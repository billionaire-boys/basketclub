package com.basketclub.security.provider;

import com.basketclub.domain.Player;
import com.basketclub.domain.SocialDetails;
import com.basketclub.security.service.SocialOauthService;
import com.basketclub.security.tokens.PostKakaoAuthenticationToken;
import com.basketclub.security.tokens.PreKakaoAuthenticationToken;
import com.basketclub.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class KakaoAuthenticationProvider implements AuthenticationProvider {
    private final SocialOauthService socialOauthService;
    private final PlayerService playerService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreKakaoAuthenticationToken token = (PreKakaoAuthenticationToken) authentication;
        SocialDetails socialDetails = socialOauthService.getSocialDetails((String) token.getPrincipal());

        if (playerService.isExistsBySocialId(socialDetails.getSocialId())) {
            return new PostKakaoAuthenticationToken(new Player(socialDetails), socialDetails);
        }

        Player registered = playerService.register(socialDetails);
        return new PostKakaoAuthenticationToken(registered, socialDetails);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return PreKakaoAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
