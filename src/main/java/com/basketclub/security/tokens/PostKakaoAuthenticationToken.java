package com.basketclub.security.tokens;

import com.basketclub.domain.Player;
import com.basketclub.domain.SocialDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;

public class PostKakaoAuthenticationToken extends UsernamePasswordAuthenticationToken {
    public PostKakaoAuthenticationToken(Player principal, SocialDetails credentials) {
        super(principal, credentials, Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    }
}
