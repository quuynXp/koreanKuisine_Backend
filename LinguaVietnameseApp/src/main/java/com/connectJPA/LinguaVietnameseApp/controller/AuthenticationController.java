package com.connectJPA.LinguaVietnameseApp.controller;


import com.connectJPA.LinguaVietnameseApp.dto.request.IntrospectRequest;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.dto.request.AuthenticationRequest;
import com.connectJPA.LinguaVietnameseApp.dto.response.AuthenticationResponse;
import com.connectJPA.LinguaVietnameseApp.dto.response.IntrospectResponse;
import com.connectJPA.LinguaVietnameseApp.entity.InvalidatedToken;
import com.connectJPA.LinguaVietnameseApp.repository.InvalidatedTokenRepository;
import com.connectJPA.LinguaVietnameseApp.service.AuthenticationService;
import com.connectJPA.LinguaVietnameseApp.service.LeaderboardService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;
    InvalidatedTokenRepository  invalidatedTokenRepository;
    LeaderboardService  leaderboardService;

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request.getEmail());
        var result = authenticationService.authenticate(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + result.getToken());

        return ResponseEntity.ok()
                .headers(headers)
                .body(ApiResponse.<AuthenticationResponse>builder()
                        .result(result)
                        .build());
    }

    @PostMapping("/firebase")
    public ResponseEntity<AuthenticationResponse> authenticateWithFirebase(@RequestHeader("Authorization") String bearerToken) {
        return ResponseEntity.ok(authenticationService.authenticateWithFirebase(bearerToken));
    }

    @PostMapping("/token/rotate")
    public ResponseEntity<AuthenticationResponse> rotate(@RequestBody IntrospectRequest request) {
        return ResponseEntity.ok(authenticationService.rotateToken(request.getToken()));
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        LocalDateTime expiry = authenticationService.extractExpiration(token);
        invalidatedTokenRepository.save(new InvalidatedToken(UUID.randomUUID().toString(), token, expiry));

        return ResponseEntity.ok().build();
    }


    @PostMapping(value = "/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestHeader("Authorization") String authorizationHeader) throws ParseException, JOSEException {
        String token = authorizationHeader.replace("Bearer ", "");

        var result = authenticationService.introspect(token);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
