package com.basketclub.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PreAuthenticationToken(String email, String password) {
        super(email, password);
    }

    public String getEmail() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
