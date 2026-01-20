package com.ecommerce.productService.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.productService.dto.ProductRequest;
import com.ecommerce.productService.dto.ProductResponse;
import com.ecommerce.productService.service.ProductService;


@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public String createProduct(@RequestBody ProductRequest productRequest) {
		String id = productService.createProduct(productRequest);
		return "Product saved successfully with id : " + id;
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable String productId){
		return ResponseEntity.ok(productService.getProductById(productId));
	}
	
	@GetMapping
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		return ResponseEntity.ok(productService.getAllProducts());
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
		productService.deleteProduct(productId);
		return ResponseEntity.ok("Product deleted successfully");
	}
}
