package com.ecommerce.productService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductEntity {
	
	@Id
	private String productId;
	
	@Column
	private String productName;
	
	@Column
	private String description;
	
	@Column
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private CategorieEntity categorieEntity;
}
