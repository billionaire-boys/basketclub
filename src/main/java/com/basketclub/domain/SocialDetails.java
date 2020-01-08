package com.basketclub.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Getter
@Embeddable
public class SocialDetails {

    @Column(name = "social_id")
    private Long socialId;

    @Enumerated(EnumType.STRING)
    private SocialProvider socialProvider;

    @Column(name = "nickname")
    private String nickname;

    @JoinColumn(name = "media_file_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private MediaFile mediaFile;

    public SocialDetails(SocialProvider socialProvider, String nickname, MediaFile mediaFile) {
        this.nickname = nickname;
        this.socialProvider = socialProvider;
        this.mediaFile = mediaFile;
    }
}
