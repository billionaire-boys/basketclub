package com.basketclub.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreKakaoAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PreKakaoAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
