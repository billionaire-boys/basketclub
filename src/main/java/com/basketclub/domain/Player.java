package com.basketclub.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PLAYER", indexes = @Index(columnList = "social_id"))
public class Player extends BaseEntity {

    @Embedded
    private SocialDetails socialDetails;

    public Player(SocialDetails socialDetails) {
        this.socialDetails = socialDetails;
    }

    @Builder
    public Player(SocialProvider socialProvider, Long socialId, String nickname, MediaFile mediaFile) {
        this.socialDetails = new SocialDetails(socialProvider, socialId, nickname, mediaFile);
    }

    public Long getSocialId() {
        return socialDetails.getSocialId();
    }

    public String getNickname() {
        return socialDetails.getNickname();
    }

    public SocialProvider getSocialProvider() {
        return socialDetails.getSocialProvider();
    }

    public String getProfileUrl() {
        return socialDetails.getMediaFile().getUrl();
    }
}
