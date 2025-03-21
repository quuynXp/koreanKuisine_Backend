package com.connectJPA.demo.controller;


import com.connectJPA.demo.dto.request.LogoutRequest;
import com.connectJPA.demo.dto.response.ApiResponse;
import com.connectJPA.demo.dto.request.AuthenticationRequest;
import com.connectJPA.demo.dto.response.AuthenticationResponse;
import com.connectJPA.demo.dto.request.IntrospectRequest;
import com.connectJPA.demo.dto.response.IntrospectResponse;
import com.connectJPA.demo.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping(value = "/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> authenticate(@RequestBody AuthenticationRequest request) {
        System.out.println(request.getUsernameOrEmailOrPhone());
        var result = authenticationService.authenticate(request);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + result.getToken());

        return ResponseEntity.ok()
                .headers(headers)
                .body(ApiResponse.<AuthenticationResponse>builder()
                        .result(result)
                        .build());
    }

    @PostMapping("/logout")
    ApiResponse<Void> logout(@RequestBody LogoutRequest request) throws ParseException, JOSEException{
        authenticationService.logout(request);
        return ApiResponse.<Void>builder()
                .build();
    }


    @PostMapping(value = "/introspect")
    public ApiResponse<IntrospectResponse> introspect(@RequestHeader("Authorization") IntrospectRequest request) throws ParseException, JOSEException {
        // Loại bỏ "Bearer " để chỉ lấy token

        var result = authenticationService.introspect(request);
        return ApiResponse.<IntrospectResponse>builder()
                .result(result)
                .build();
    }

}
