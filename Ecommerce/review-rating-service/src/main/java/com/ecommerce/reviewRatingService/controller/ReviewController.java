package com.ecommerce.reviewRatingService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.reviewRatingService.dto.ReviewRequest;
import com.ecommerce.reviewRatingService.dto.ReviewResponse;
import com.ecommerce.reviewRatingService.service.ReviewService;

@RestController
public class ReviewController {
	
	@Autowired
	private ReviewService reviewService;
	
	@PostMapping
	public ResponseEntity<String> addReview(@RequestBody ReviewRequest reviewRequest){
		int id = reviewService.addReview(reviewRequest);
		return ResponseEntity.ok("Review added with id : " + id);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<ReviewResponse>> getReviews(@PathVariable String productId){
		return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
	}
	
}
