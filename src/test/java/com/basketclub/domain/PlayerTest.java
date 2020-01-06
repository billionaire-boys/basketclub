package com.basketclub.domain;


import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {
    @Test
    void 업데이트() {
        Player origin = new Player("이름", "이메일", "패스워드");
        Player edit = new Player("update", "update", "update");

        origin.update(edit);

        assertThat(edit.getName()).isEqualTo("update");
        assertThat(edit.getEmail()).isEqualTo("update");
        assertThat(edit.getPassword()).isEqualTo("update");
    }

    @Test
    void matchPassword() {
        Player player = new Player("name", "email@email.com", "password");
        assertThat(player.isMatchPassword("password")).isTrue();
    }
}