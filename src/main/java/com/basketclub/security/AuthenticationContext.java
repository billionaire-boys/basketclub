package com.basketclub.security;

import com.basketclub.domain.Player;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;

public class AuthenticationContext extends User {

    private AuthenticationContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public static AuthenticationContext from(Player player) {
        return new AuthenticationContext(player.getEmail(), player.getPassword(), new ArrayList<>());
    }
}
