package com.ecommerce.reviewRatingService.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.reviewRatingService.dto.ReviewRequest;
import com.ecommerce.reviewRatingService.dto.ReviewResponse;
import com.ecommerce.reviewRatingService.entity.ReviewEntity;
import com.ecommerce.reviewRatingService.repository.ReviewRepository;

@Service
public class ReviewService {
	
	@Autowired
	private ReviewRepository reviewRepository;

	@Transactional
	public int addReview(ReviewRequest reviewRequest) {
		ReviewEntity review = ReviewEntity.builder()
                .productId(reviewRequest.getProductId())
                .userId(reviewRequest.getUserId())
                .rating(reviewRequest.getRating())
                .comment(reviewRequest.getComment())
                .build();

        ReviewEntity saved = reviewRepository.save(review);
        return saved.getId();
		
	}

	public List<ReviewResponse> getReviewsByProductId(String productId) {
        List<ReviewEntity> reviews = reviewRepository.findByProductId(productId);

        return reviews.stream().map(review ->
                ReviewResponse.builder()
                        .userId(review.getUserId())
                        .rating(review.getRating())
                        .comment(review.getComment())
                        .createdAt(review.getCreatedAt())
                        .build()
        ).toList();
    }

}
