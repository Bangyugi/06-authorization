package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.AuthenticationRequest;
import com.bangvan.demologin.dto.request.IntrospectRequest;
import com.bangvan.demologin.dto.respond.AuthenticationResponse;
import com.bangvan.demologin.dto.respond.IntrospectRespond;
import com.nimbusds.jose.JOSEException;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.text.ParseException;

public interface AuthenticationService {

    IntrospectRespond introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException;

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
