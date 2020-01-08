package com.basketclub.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PLAYER", indexes = @Index(columnList = "social_id"))
public class Player extends BaseEntity {

    @Embedded
    private SocialDetails socialDetails;

    public Player(SocialDetails socialDetails) {
        this.socialDetails = socialDetails;
    }

    public Player(SocialProvider provider, String nickname, MediaFile mediaFile) {
        this.socialDetails = new SocialDetails(provider, nickname, mediaFile);
    }
}
