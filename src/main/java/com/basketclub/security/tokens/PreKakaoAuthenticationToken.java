package com.basketclub.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreKakaoAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PreKakaoAuthenticationToken(String code, String dumb) {
        super(code, dumb);
    }
}
