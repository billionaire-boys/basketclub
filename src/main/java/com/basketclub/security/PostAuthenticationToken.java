package com.basketclub.security;

import com.basketclub.domain.Player;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PostAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PostAuthenticationToken(String email, String password) {
        super(email, password);
    }

    public PostAuthenticationToken(Player player) {
        super(player.getEmail(), player.getPassword());
    }

    public String getEmail() {
        return (String) super.getPrincipal();
    }

    public String getPassword() {
        return (String) super.getCredentials();
    }
}
