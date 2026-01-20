package com.ecommerce.productService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
	private String productName;
	private String description;
	private double price;
	private CategorieResponse categorieResponse;
	private List<ReviewResponse> reviewResponse;
}
