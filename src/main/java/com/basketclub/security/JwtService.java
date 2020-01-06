package com.basketclub.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;

@Slf4j
@Component
public class JwtService {
    private static String SIGNKEY = "andole-jwt-test";

    public String generate(PostAuthenticationToken token) {
        try {
            return JWT.create()
                    .withIssuer("andole")
                    .withClaim("email", token.getEmail())
                    .withClaim("password", token.getPassword())
                    .sign(Algorithm.HMAC256(SIGNKEY));
        } catch (UnsupportedEncodingException e) {
            log.error(e.getMessage());
            throw new IllegalArgumentException();
        }
    }
}
