package com.basketclub.web.controller;

import com.basketclub.domain.Review;
import com.basketclub.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/players")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> findAll() {
        List<Review> all = reviewService.findAll();
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{playerId}/reviews")
    public ResponseEntity<List<Review>> findByPlayerId(@PathVariable("playerId") long playerId) {
        List<Review> reviews = reviewService.findByTargetId(playerId);
        return ResponseEntity.ok(reviews);
    }
}
