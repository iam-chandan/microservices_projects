package com.ecommerce.productService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.productService.client.ReviewClient;
import com.ecommerce.productService.dto.CategorieResponse;
import com.ecommerce.productService.dto.ProductRequest;
import com.ecommerce.productService.dto.ProductResponse;
import com.ecommerce.productService.entity.CategorieEntity;
import com.ecommerce.productService.entity.ProductEntity;
import com.ecommerce.productService.repository.CategoriesRepository;
import com.ecommerce.productService.repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	@Autowired
	private ReviewClient reviewClient;


	@Transactional
	public String createProduct(ProductRequest productRequest) {
		
		String categorie = productRequest.getCategorieName();
		
		CategorieEntity catEntity = categoriesRepository.findByNameIgnoreCase(categorie)
				.orElseGet(() -> {
					CategorieEntity caEntity = new CategorieEntity();
					
					caEntity.setName(categorie);
					return categoriesRepository.save(caEntity);
				});
		ProductEntity productEntity = new ProductEntity();
		productEntity.setProductId(UUID.randomUUID().toString());
		productEntity.setProductName(productRequest.getProductName());
		productEntity.setDescription(productRequest.getDescription());
		productEntity.setPrice(productRequest.getPrice());
		productEntity.setCategorieEntity(catEntity);
		
		ProductEntity product = productRepository.save(productEntity);
		
		return product.getProductId();
	}

	public ProductResponse getProductById(String productId) {
		ProductEntity productEntity = productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("product not found with id : " + productId));
		return ProductResponse.builder()
				.productName(productEntity.getProductName())
				.description(productEntity.getDescription())
				.price(productEntity.getPrice())
				.categorieResponse(
						new CategorieResponse(productEntity.getCategorieEntity().getName())
						)
				.reviewResponse(
						reviewClient.getReviews(productId)
						)
				.build();
	}

	public List<ProductResponse> getAllProducts() {
		List<ProductEntity> products = productRepository.findAll();
		
		return products.stream().map(product -> {
			return ProductResponse.builder()
					.productName(product.getProductName())
					.description(product.getDescription())
					.price(product.getPrice())
					.categorieResponse(
							new CategorieResponse(product.getCategorieEntity().getName())
							)
					.reviewResponse(
							reviewClient.getReviews(product.getProductId())
							)
					.build();
		}).toList();
	}

	public void deleteProduct(String productId) {
		productRepository.deleteById(productId);
	}
	

}
