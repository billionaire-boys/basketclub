package com.basketclub.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MediaFileTest {
    @Test
    void defaultIfEmpty() {
        String url = "";

        MediaFile empty = new MediaFile(url);

        assertThat(empty.getUrl()).isNotEqualTo(url);

        url = null;

        MediaFile nullFiles = new MediaFile(url);

        assertThat(nullFiles.getUrl()).isNotNull();
    }
}