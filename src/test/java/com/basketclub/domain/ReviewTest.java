package com.basketclub.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class ReviewTest {
    @Autowired
    private TestEntityManager tm;

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .socialProvider(SocialProvider.KAKAO)
                .socialId(1L)
                .nickname("nickname")
                .mediaFile(new MediaFile(""))
                .build();
    }

    @Test
    void manyToOneByPlayer() {
        tm.persist(player);
        Long playerId = tm.getId(player, Long.class);

        Review review1 = new Review(player, ReviewScore.EVIL, "악마");
        Review review2 = new Review(player, ReviewScore.EXCELLENT, "훌륭");
        Review review3 = new Review(player, ReviewScore.COMMON, "보통");

        tm.persist(review1);
        tm.persist(review2);
        tm.persist(review3);

        assertThat(review1.getTarget().getId()).isEqualTo(playerId);
        assertThat(review2.getTarget().getId()).isEqualTo(playerId);
        assertThat(review3.getTarget().getId()).isEqualTo(playerId);
    }

    @Test
    void doesNotSupportCascadePersist() {
        Review review = new Review(player, ReviewScore.EVIL, "악마");

        assertThat(player.getId()).isNull();
        assertThatThrownBy(() -> tm.persist(review)).isInstanceOf(IllegalStateException.class);
    }
}