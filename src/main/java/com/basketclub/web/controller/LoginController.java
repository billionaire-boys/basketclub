package com.basketclub.web.controller;

import com.basketclub.domain.Player;
import com.basketclub.security.OauthProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class LoginController {
    private final OauthProperties oauthProperties;

    @GetMapping
    public String hello() {
        return "Let's Play Game!";
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        UriComponents build = UriComponentsBuilder.fromHttpUrl(oauthProperties.getRequestUrl())
                .queryParam("client_id", oauthProperties.getClientId())
                .queryParam("redirect_uri", oauthProperties.getRedirectUrl())
                .queryParam("response_type", "code")
                .build();
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("oauthProviderUrl", build);
        return mv;
    }

    @GetMapping("/welcome")
    public String welcome() {
        Player authentication = (Player) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authentication.toString();
    }
}
