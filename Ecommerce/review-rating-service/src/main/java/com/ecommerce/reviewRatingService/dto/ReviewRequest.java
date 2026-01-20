package com.ecommerce.reviewRatingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequest {
    private String productId;
    private String userId;
    private int rating;
    private String comment;
}

