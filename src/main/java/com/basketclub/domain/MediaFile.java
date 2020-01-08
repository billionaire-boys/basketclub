package com.basketclub.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class MediaFile extends BaseEntity {
    private static final String DEFAULT_URL = "https://avatars1.githubusercontent.com/u/37759759?s=460&v=4";
    private String url;

    public MediaFile(String url) {
        this.url = defaultIfEmpty(url);
    }

    private String defaultIfEmpty(String url) {
        if (url == null || url.isEmpty()) {
            return DEFAULT_URL;
        }
        return url;
    }
}
