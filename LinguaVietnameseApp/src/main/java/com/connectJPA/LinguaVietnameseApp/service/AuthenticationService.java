package com.connectJPA.LinguaVietnameseApp.service;

import com.connectJPA.LinguaVietnameseApp.dto.request.AuthenticationRequest;
import com.connectJPA.LinguaVietnameseApp.dto.response.AuthenticationResponse;
import com.connectJPA.LinguaVietnameseApp.dto.response.IntrospectResponse;
import com.connectJPA.LinguaVietnameseApp.dto.response.UserResponse;
import com.connectJPA.LinguaVietnameseApp.entity.InvalidatedToken;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.entity.UserRole;
import com.connectJPA.LinguaVietnameseApp.exception.AppException;
import com.connectJPA.LinguaVietnameseApp.exception.ErrorCode;
import com.connectJPA.LinguaVietnameseApp.repository.InvalidatedTokenRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRepository;
import com.connectJPA.LinguaVietnameseApp.repository.UserRoleRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.StringJoiner;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    PasswordEncoder passwordEncoder;
    UserRepository userRepository;
    UserService userService;
    InvalidatedTokenRepository  invalidatedTokenRepository;
    UserRoleRepository userRoleRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(String token) throws ParseException, JOSEException {
        try {
            // Kiểm tra token có nằm trong danh sách đã bị vô hiệu hóa không
            if (invalidatedTokenRepository.existsByToken(token)) {
                return IntrospectResponse.builder()
                        .valid(false)
                        .userName(null)
                        .build();
            }

            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            boolean verified = signedJWT.verify(verifier);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            boolean valid = verified && expiryTime.after(new Date());

            return IntrospectResponse.builder()
                    .valid(valid)
                    .userName(valid ? getUsernameFromJwt(token) : null)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }

    public AuthenticationResponse authenticateWithFirebase(String bearerToken) {
        String idToken = bearerToken.replace("Bearer ", "");

        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String email = decodedToken.getEmail();
            String uid = decodedToken.getUid();

            // TODO: Tìm hoặc tạo user trong hệ thống
            User user = userService.findOrCreateUserByEmail(email);

            // Tạo JWT nội bộ của hệ thống
            String jwt = generateToken(user);

            return AuthenticationResponse.builder()
                    .token(jwt)
                    .build();
        } catch (FirebaseAuthException e) {
            return AuthenticationResponse.builder()
                    .authenticated(false)
                    .build();
        }
    }

    public AuthenticationResponse rotateToken(String currentToken) {
        try {
            // Kiểm tra token đã bị vô hiệu hóa chưa
            if (invalidatedTokenRepository.existsByToken(currentToken)) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }

            SignedJWT signedJWT = SignedJWT.parse(currentToken);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            boolean verified = signedJWT.verify(verifier);
            Date expiry = signedJWT.getJWTClaimsSet().getExpirationTime();

            if (!verified || expiry.before(new Date())) {
                throw new AppException(ErrorCode.UNAUTHENTICATED);
            }

            String username = signedJWT.getJWTClaimsSet().getSubject();
            User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

            // Đưa token hiện tại vào danh sách bị vô hiệu hoá
            invalidatedTokenRepository.save(
                    InvalidatedToken.builder()
                            .token(currentToken)
                            .expiryTime(LocalDateTime.now())
                            .build()
            );

            // Cấp token mới
            String newToken = generateToken(user);

            return AuthenticationResponse.builder()
                    .token(newToken)
                    .authenticated(true)
                    .build();

        } catch (Exception e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }




    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        User foundUser = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        boolean authenticated = passwordEncoder.matches(request.getPassword(), foundUser.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        String token = generateToken(foundUser);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    public String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer(".com") //domain
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope", buildScope(user))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsernameFromJwt(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject(); // Lấy username từ subject
        } catch (ParseException e) {
            throw new RuntimeException("Failed to parse JWT token", e);
        }
    }

    private String buildScope(User user){
        StringJoiner stringJoiner = new StringJoiner(" ");

        if(!CollectionUtils.isEmpty(userRoleRepository.findAllIdUserId(user.getUserId()))) {
            for (UserRole userRole : userRoleRepository.findAllIdUserId(user.getUserId())
                    .stream()
                    .toList()) {
                stringJoiner.add((CharSequence) userRole);
            }
        }

        return stringJoiner.toString();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromJwt(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    public LocalDateTime extractExpiration(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            Date date = signedJWT.getJWTClaimsSet().getExpirationTime();
            return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
        } catch (Exception e) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }


}
