package com.basketclub.domain;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(of = "id")
@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReviewScore score;

    private String contents;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Player target;

    @Builder
    public Review(ReviewScore score, String contents, Player target) {
        this.score = score;
        this.contents = contents;
        this.target = target;
    }
}
