package com.basketclub.security;

import com.basketclub.domain.Player;
import com.basketclub.domain.PlayerRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class FormLoginAuthenticationProvider implements AuthenticationProvider {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final PlayerRepository playerRepository;

    public FormLoginAuthenticationProvider(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthenticationToken preToken = (PreAuthenticationToken) authentication;
        Player player = playerRepository.findByEmail(preToken.getEmail()).orElseThrow(IllegalArgumentException::new);

        if (isCorrectPlayer(player, preToken)) {
            return new PostAuthenticationToken(player.getEmail(), player.getPassword());
        }
        throw new IllegalArgumentException();
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreAuthenticationToken.class.isAssignableFrom(authentication);
    }

    private boolean isCorrectPlayer(Player player, PreAuthenticationToken token) {
        return token.getPassword().equals(player.getPassword());
    }
}
