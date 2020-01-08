package com.basketclub.security;

import lombok.Data;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Data
@Component
@PropertySource(value = "classpath:oauth.yml", factory = YmlPropertiesFactory.class)
public class OauthProperties implements InitializingBean {

    @Value("${client.requestUrl:#{null}}")
    private String requestUrl;

    @Value("${client.accessTokenUrl:#{null}}")
    private String accessTokenUrl;

    @Value("${client.clientId:#{null}}")
    private String clientId;

    @Value("${client.redirectUrl:#{null}}")
    private String redirectUrl;

    @Value("${client.resourceUrl:#{null}}")
    private String resourceUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        if (hasNullField()) {
            throw new BeanCreationException("oauth.yml 설정이 필요합니다");
        }
    }

    private boolean hasNullField() {
        return Arrays.stream(getClass().getDeclaredFields())
                .anyMatch(field -> {
                    try {
                        return field.get(this) == null;
                    } catch (IllegalAccessException e) {
                        return true;
                    }
                });
    }
}