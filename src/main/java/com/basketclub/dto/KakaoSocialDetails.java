package com.basketclub.dto;

import com.basketclub.domain.MediaFile;
import com.basketclub.domain.SocialDetails;
import com.basketclub.domain.SocialProvider;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class KakaoSocialDetails {
    @JsonProperty("id")
    private Long socialId;

    private String nickname;

    private String profileUrl;

    @JsonProperty("properties")
    public void setNickname(Map<String, Object> properties) {
        this.nickname = (String) properties.get("nickname");
    }

    public SocialDetails toEntity() {
        return new SocialDetails(SocialProvider.KAKAO, socialId, nickname, new MediaFile(profileUrl));
    }
}
