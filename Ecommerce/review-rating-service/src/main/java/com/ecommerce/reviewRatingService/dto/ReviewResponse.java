package com.ecommerce.reviewRatingService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewResponse {
    private String userId;
    private int rating;
    private String comment;
    private LocalDateTime createdAt;
}
