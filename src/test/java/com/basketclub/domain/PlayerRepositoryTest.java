package com.basketclub.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
class PlayerRepositoryTest {
    @Autowired
    private PlayerRepository playerRepository;

    private Player player;

    @BeforeEach
    void setUp() {
        player = Player.builder()
                .name("name")
                .email("test@email.com")
                .password("password")
                .build();
    }

    @Test
    void unique() {
        playerRepository.save(player);
        Player duplicate = Player.builder()
                .name("name2")
                .email("test@email.com")
                .password("password2")
                .build();
        assertThatThrownBy(() -> playerRepository.save(duplicate));
    }

    @Test
    void CRUD() {
        Player persist = playerRepository.save(player);
        assertThat(persist.getId()).isNotNull();
        assertThat(persist.getId()).isNotNull();
        assertThat(persist.getName()).isEqualTo("name");
        assertThat(persist.getEmail()).isEqualTo("test@email.com");
        assertThat(persist.getPassword()).isEqualTo("password");

        Player another = Player.builder()
                .name("update")
                .email("update@email.com")
                .password("update")
                .build();

        persist.update(another);
        playerRepository.flush();

        Optional<Player> updated = playerRepository.findByEmail(persist.getEmail());

        assertThat(updated.isPresent()).isTrue();
        persist = updated.get();

        assertThat(persist.getName()).isEqualTo("update");
        assertThat(persist.getEmail()).isEqualTo("update@email.com");
        assertThat(persist.getPassword()).isEqualTo("update");

        playerRepository.delete(persist);
        playerRepository.flush();

        Optional<Player> deleted = playerRepository.findByEmail(persist.getEmail());

        assertThat(deleted.isPresent()).isFalse();
    }

    @Test
    void exists() {
        playerRepository.save(player);
        boolean exists = playerRepository.existsPlayerByEmail("test@email.com");
        assertThat(exists).isTrue();
    }
}