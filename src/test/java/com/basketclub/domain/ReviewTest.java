package com.basketclub.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewTest {
    @Autowired
    private TestEntityManager tm;

    @Test
    void relations() {
        Player player = new Player("name", "some@email.com", "password");
        tm.persistAndFlush(player);

        Review review = Review.builder()
                .score(ReviewScore.EVIL)
                .contents("악마")
                .target(player)
                .build();

        tm.persist(review);

        assertThat(review.getTarget().getId()).isEqualTo(player.getId());
    }
}