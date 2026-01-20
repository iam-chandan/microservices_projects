package com.ecommerce.productService.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ecommerce.productService.dto.ReviewResponse;

@FeignClient(name = "review-rating-service")
public interface ReviewClient {
	
	@GetMapping("/product/{productId}")
	List<ReviewResponse> getReviews(@PathVariable String productId);
}
