package com.basketclub.domain.repository;

import com.basketclub.domain.MediaFile;
import com.basketclub.domain.Player;
import com.basketclub.domain.SocialProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

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
    void findBySocialId() {
        playerRepository.save(player);
        assertThat(player.getId()).isNotNull();

        Optional<Player> findById = playerRepository.findById(player.getId());
        Optional<Player> findBySocialId = playerRepository.findBySocialDetailsSocialId(1L);

        assertThat(findById.isPresent()).isTrue();
        assertThat(findBySocialId.isPresent()).isTrue();

        assertThat(findBySocialId.get()).isEqualTo(findById.get());
    }
}