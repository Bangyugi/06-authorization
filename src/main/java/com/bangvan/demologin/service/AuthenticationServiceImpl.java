package com.bangvan.demologin.service;

import com.bangvan.demologin.dto.request.AuthenticationRequest;
import com.bangvan.demologin.dto.request.IntrospectRequest;
import com.bangvan.demologin.dto.respond.AuthenticationResponse;
import com.bangvan.demologin.dto.respond.IntrospectRespond;
import com.bangvan.demologin.entity.User;
import com.bangvan.demologin.exception.AppException;
import com.bangvan.demologin.exception.ErrorCode;
import com.bangvan.demologin.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceImpl.class);
    @Autowired
    private UserRepository userRepository;

    @NonFinal
    @Value("${jwt.signerKey}")
    protected String SIGNER_KEY ;

    @Override
    public IntrospectRespond introspect(IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        var token = introspectRequest.getToken();
        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);

        Date expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

        var verified = signedJWT.verify(verifier);

        return IntrospectRespond.builder()
                .valid(verified && expiration.after(new Date()))
                .build();

    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        User user = userRepository.findByUsername(authenticationRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.USERNAME_NOT_EXISTED));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        boolean authenticated = passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword());
        if (!authenticated) {
            throw new AppException(ErrorCode.UNAUTHENTICATED);
        }
        var token = generateToken(user); // Kiểu dữ liệu var giống với auto bên c++
        return AuthenticationResponse.builder()
                .token(token)
                .authenticated(true)
                .build();
    }

    // Hàm tạo token
    private String generateToken(User user) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS256); // JWSHeader được tạo ra chỉ định thuật toán (HS256 trong trường hợp này).

        //body
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder() // JWTClaimsSet được sử dụng để xây dựng các claims (dữ liệu) của token
                .subject(user.getUsername()) // subject: Tên đăng nhập.
                .issuer("bangvan.com") // issuer: Xác định nhà phát hành token.
                .issueTime(new Date()) //issueTime: Thời gian hiện tại khi token được phát hành
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                )) //expirationTime: Thiết lập token hết hạn sau 1 giờ.
                .claim("scope", user.rolesToString()) //claim: Thêm một claim tùy chỉnh.
                .build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject()); //jwtClaimsSet được chuyển đổi thành Payload.
        JWSObject jwsObject = new JWSObject(header, payload); //Một JWSObject được tạo ra với header và payload.

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes())); //JWSObject được ký bằng MACSigner và khóa ký với SIGNER_KEY là chuỗi 32byte
            return jwsObject.serialize();
        }catch (JOSEException e) {
            log.error("Cannot sign JWT object", e);
            throw new RuntimeException(e);
        }
    }

//    private String buildScope(User user) {
//
//    }

}
