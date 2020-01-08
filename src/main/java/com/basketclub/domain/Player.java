package com.basketclub.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "PLAYER", indexes = @Index(columnList = "social_id"))
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private SocialDetails socialDetails;

    public Player(SocialDetails socialDetails) {
        this.socialDetails = socialDetails;
    }

    public Player(SocialProvider provider, String nickname, MediaFile mediaFile) {
        this.socialDetails = new SocialDetails(provider, nickname, mediaFile);
    }
}
