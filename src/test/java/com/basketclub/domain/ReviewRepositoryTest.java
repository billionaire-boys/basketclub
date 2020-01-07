package com.basketclub.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository repository;
    @Autowired
    private TestEntityManager tm;
    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .name("강백호")
                .email("kk@email.com")
                .password("password")
                .build();
        tm.persist(player);
    }

    @Test
    void findByTargetEmail() {
        repository.saveAll(Arrays.asList(new Review(player, ReviewScore.EVIL, "악마"),
                new Review(player, ReviewScore.EXCELLENT, "천사"),
                new Review(player, ReviewScore.COMMON, "보통")));

        List<Review> result = repository.findByTargetEmail("kk@email.com");

        assertThat(result.size()).isEqualTo(3);
    }
}