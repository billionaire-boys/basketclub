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

    @Column(name = "nickname")
    private String nickname;

    @JoinColumn(name = "media_file_id")
    @OneToOne(cascade = CascadeType.PERSIST)
    private MediaFile mediaFile;

    @Builder
    public SocialDetails(Long socialId, String nickname, MediaFile mediaFile) {
        this.socialId = socialId;
        this.nickname = nickname;
        this.mediaFile = mediaFile;
    }
}
