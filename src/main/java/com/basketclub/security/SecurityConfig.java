package com.basketclub.security;

import com.basketclub.security.filter.KakaoAuthenticationFilter;
import com.basketclub.security.provider.KakaoAuthenticationProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final KakaoAuthenticationProvider kakaoAuthenticationProvider;
    private final KakaoAuthenticationFilter kakaoAuthenticationFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(kakaoAuthenticationProvider());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/css/**", "/image/**", "/js/**", "/favicon.ico");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .and()
                .logout()
                .and()
                .addFilterBefore(kakaoAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    private Filter kakaoAuthenticationFilter() {
        return kakaoAuthenticationFilter;
    }

    private AuthenticationProvider kakaoAuthenticationProvider() {
        return kakaoAuthenticationProvider;
    }
}
