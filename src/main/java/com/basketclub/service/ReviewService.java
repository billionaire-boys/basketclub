package com.basketclub.service;

import com.basketclub.domain.Review;
import com.basketclub.domain.repository.ReviewRepository;
import com.basketclub.dto.ReviewRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final PlayerService playerService;

    @Transactional(readOnly = true)
    public List<Review> findByTargetId(long id) {
        return reviewRepository.findByTargetId(id);
    }

    public Review add(long playerId, ReviewRequestDto requestDto) throws NoSuchEntityException {
        return reviewRepository.save(requestDto.toEntity(playerService.findByPlayerId(playerId)));
    }

    public Review findById(long reviewId) throws NoSuchEntityException {
        return reviewRepository.findById(reviewId).orElseThrow(() -> new NoSuchReviewException("해당 리뷰가 없습니다."));
    }

    public void deleteById(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}


