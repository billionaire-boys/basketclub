package com.basketclub.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordEncoderTest {
    private PasswordEncoder encoder;

    @BeforeEach
    void setUp() {
        encoder = new BCryptPasswordEncoder();
    }

    @Test
    void encode() {
        String original = "password";
        String password = encoder.encode(original);

        assertThat(original).isNotEqualTo(password);
    }

    @Test
    void matches() {
        String original = "password";
        String password = encoder.encode(original);

        assertThat(encoder.matches(original, password)).isTrue();
    }
}