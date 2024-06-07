package com.bangvan.demologin.controller;

import com.bangvan.demologin.dto.request.ApiResponse;
import com.bangvan.demologin.dto.request.AuthenticationRequest;
import com.bangvan.demologin.dto.request.IntrospectRequest;
import com.bangvan.demologin.dto.respond.AuthenticationResponse;
import com.bangvan.demologin.dto.respond.IntrospectRespond;
import com.bangvan.demologin.service.AuthenticationService;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static java.util.stream.Stream.builder;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        var result = authenticationService.authenticate(authenticationRequest);
        ApiResponse<AuthenticationResponse> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }

    @PostMapping("/introspect")
    public ApiResponse<IntrospectRespond> authenticate(@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        var result = authenticationService.introspect(introspectRequest);
        ApiResponse<IntrospectRespond> apiResponse = new ApiResponse<>();
        apiResponse.setResult(result);
        return apiResponse;
    }
}
