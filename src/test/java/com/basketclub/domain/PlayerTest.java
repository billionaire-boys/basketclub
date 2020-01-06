package com.basketclub.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@DataJpaTest
class PlayerTest {
    @Autowired
    private TestEntityManager tm;

    @Test
    void createFromSocialDetail() {
        SocialDetails details = SocialDetails.builder()
                .socialProvider(SocialProvider.KAKAO)
                .nickname("nickname")
                .socialId(1L)
                .mediaFile(new MediaFile(""))
                .build();

        Player player = new Player(details);

        assertThat(player.getSocialProvider()).isEqualTo(details.getSocialProvider());
        assertThat(player.getSocialId()).isEqualTo(details.getSocialId());
        assertThat(player.getNickname()).isEqualTo(details.getNickname());
        assertThat(player.getProfileUrl()).isEqualTo(details.getMediaFile().getUrl());

        Player another = Player.builder()
                .socialProvider(details.getSocialProvider())
                .socialId(details.getSocialId())
                .nickname(details.getNickname())
                .mediaFile(details.getMediaFile())
                .build();

        assertThat(another.getSocialProvider()).isEqualTo(details.getSocialProvider());
        assertThat(another.getSocialId()).isEqualTo(details.getSocialId());
        assertThat(another.getNickname()).isEqualTo(details.getNickname());
        assertThat(another.getProfileUrl()).isEqualTo(details.getMediaFile().getUrl());
    }

    @Test
    void cascadePersist() {
        MediaFile mediaFile = new MediaFile("");
        Player player = Player.builder()
                .socialProvider(SocialProvider.KAKAO)
                .nickname("nickname")
                .socialId(1L)
                .mediaFile(mediaFile)
                .build();

        assertDoesNotThrow(() -> tm.persist(player));
        assertThat(player.getId()).isNotNull();
        assertThat(mediaFile.getId()).isNotNull();
    }
}