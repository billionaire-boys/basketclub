package com.basketclub.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PostKakaoAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PostKakaoAuthenticationToken(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
