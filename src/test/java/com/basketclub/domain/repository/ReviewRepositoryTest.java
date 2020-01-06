package com.basketclub.domain.repository;

import com.basketclub.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

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
        tm.persist(player);
    }

    @Test
    void findByTargetId() {
        Review review = Review.builder()
                .target(player)
                .score(ReviewScore.EVIL)
                .contents("나쁜놈")
                .build();

        reviewRepository.save(review);
        assertThat(review.getId()).isNotNull();

        List<Review> reviews = reviewRepository.findByTargetId(player.getId());
        assertThat(reviews.size()).isEqualTo(1);

        reviewRepository.delete(review);
        List<Review> deleted = reviewRepository.findByTargetId(player.getId());
        assertThat(deleted.isEmpty()).isTrue();
    }
}