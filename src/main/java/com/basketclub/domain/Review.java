package com.basketclub.domain;

import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ReviewScore score;

    @Lob
    private String contents;

    @ManyToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Player target;

    @Builder
    public Review(Player target, ReviewScore score, String contents) {
        this.target = target;
        this.score = score;
        this.contents = contents;
    }
}
