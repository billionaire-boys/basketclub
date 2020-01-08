package com.basketclub.dto;

import com.basketclub.domain.Player;
import com.basketclub.domain.Review;
import com.basketclub.domain.ReviewScore;
import lombok.Data;

@Data
public class ReviewRequestDto {
    private ReviewScore reviewScore;
    private String contents;

    public Review toEntity(Player player) {
        return new Review(player, reviewScore, contents);
    }
}
