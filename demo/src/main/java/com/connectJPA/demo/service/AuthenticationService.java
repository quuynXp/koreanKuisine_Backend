package com.connectJPA.demo.service;

import com.connectJPA.demo.dto.request.AuthenticationRequest;
import com.connectJPA.demo.dto.response.AuthenticationResponse;
import com.connectJPA.demo.dto.request.IntrospectRequest;
import com.connectJPA.demo.dto.response.IntrospectResponse;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.exception.AppException;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.repository.UserRepository;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.Date;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY;

    public IntrospectResponse introspect(String token) throws ParseException, JOSEException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

            boolean verified = signedJWT.verify(verifier);
            Date expiryTime = signedJWT.getJWTClaimsSet().getExpirationTime();

            return IntrospectResponse.builder()
                    .valid(verified && expiryTime.after(new Date()))
                    .userName(getUsernameFromJwt(token))
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
    }



    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        String username = request.getUsername();
        String mail = request.getMail();
        String phone = request.getPhone();

        Optional<User> user = Optional.empty();
        if (mail == null && phone == null) {
            user = userRepository.findByUsername(username);
        } else {
            user = userRepository.findByUsernameOrMailOrPhone(username, mail, phone);
        }

        User foundUser = user.orElseThrow(() -> {
            System.out.println("User not found with provided credentials.");
            return new AppException(ErrorCode.USER_NOT_EXISTED);
        });

        boolean authenticated = passwordEncoder.matches(request.getPassword(), foundUser.getPassword());

        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }

        // Tạo và trả về token
        var token = generateToken(foundUser);

        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }


    private String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .issuer("koreankuisine.com") //domain
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
        StringJoiner stringJoiner = new StringJoiner("");

        if(!CollectionUtils.isEmpty(user.getRoles())){
            user.getRoles().forEach(stringJoiner::add);


        }
        return stringJoiner.toString();
    }

    public Authentication getAuthentication(String token) {
        String username = getUsernameFromJwt(token);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
        return new UsernamePasswordAuthenticationToken(user, null, Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }


}
