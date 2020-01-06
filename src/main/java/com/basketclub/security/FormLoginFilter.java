package com.basketclub.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FormLoginFilter extends AbstractAuthenticationProcessingFilter {
    private FormLoginSuccessHandler successHandler;
    private FormLoginFailHandler failHandler;

    public FormLoginFilter(String defaultFilterProcessesUrl, FormLoginSuccessHandler successHandler, FormLoginFailHandler failHandler) {
        super(defaultFilterProcessesUrl);
        this.successHandler = successHandler;
        this.failHandler = failHandler;
    }

    public FormLoginFilter(RequestMatcher requiresAuthenticationRequestMatcher, FormLoginSuccessHandler successHandler, FormLoginFailHandler failHandler) {
        super(requiresAuthenticationRequestMatcher);
        this.successHandler = successHandler;
        this.failHandler = failHandler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        PreAuthenticationToken token = new PreAuthenticationToken(request.getParameter("email"), request.getParameter("password"));
        return this.getAuthenticationManager().authenticate(token);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult) throws IOException, ServletException {
        this.successHandler.onAuthenticationSuccess(request, response, chain, authResult);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) throws IOException, ServletException {
        this.failHandler.onAuthenticationFailure(request, response, failed);
    }
}
