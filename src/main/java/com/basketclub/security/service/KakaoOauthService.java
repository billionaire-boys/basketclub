package com.basketclub.security.service;

import com.basketclub.domain.SocialDetails;
import com.basketclub.dto.KakaoSocialDetails;
import com.basketclub.security.OauthProperties;
import com.basketclub.security.tokens.KakaoAccessToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class KakaoOauthService implements SocialOauthService {
    private final OauthProperties oauthProperties;
    private WebClient webClient;

    public KakaoOauthService(OauthProperties oauthProperties) {
        this.oauthProperties = oauthProperties;
        this.webClient = WebClient.builder()
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .filters(exchangeFilterFunctions -> exchangeFilterFunctions.add(logRequest()))
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            StringBuilder sb = new StringBuilder("Request: \n");
            clientRequest
                    .headers()
                    .forEach((name, values) -> values.forEach(value -> sb.append(name).append(" : ").append(value)));
            log.debug(sb.toString());
            return Mono.just(clientRequest);
        });
    }

    @Override
    public SocialDetails getSocialDetails(String code) {
        Mono<String> accessToken = webClient.post()
                .uri(oauthProperties.getAccessTokenUrl())
                .body(BodyInserters
                        .fromFormData("grant_type", "authorization_code")
                        .with("client_id", oauthProperties.getClientId())
                        .with("redirect_uri", oauthProperties.getRedirectUrl())
                        .with("code", code)
                )
                .retrieve()
                .bodyToMono(KakaoAccessToken.class)
                .map(KakaoAccessToken::getAccessToken);

        Mono<KakaoSocialDetails> details = webClient.get().uri(oauthProperties.getResourceUrl())
                .headers(httpHeaders -> httpHeaders.setBearerAuth(accessToken.block()))
                .retrieve()
                .bodyToMono(KakaoSocialDetails.class);

        SocialDetails block = details.map(KakaoSocialDetails::toEntity).block();
        log.error("RESULT : ", block);
        return block;
    }
}
