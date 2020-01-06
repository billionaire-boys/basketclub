package com.basketclub.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = OauthProperties.class)
class OauthPropertiesTest {
    @Autowired
    private OauthProperties oauthProperties;

    @Test
    void 프로퍼티_불러오기() {
        assertThat(oauthProperties.getAccessTokenUrl()).isNotEmpty();
        assertThat(oauthProperties.getClientId()).isNotEmpty();
        assertThat(oauthProperties.getRedirectUrl()).isNotEmpty();
        assertThat(oauthProperties.getResourceUrl()).isNotEmpty();
    }
}