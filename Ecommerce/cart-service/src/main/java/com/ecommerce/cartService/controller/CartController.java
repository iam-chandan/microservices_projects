package com.ecommerce.cartService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.cartService.dto.CartRequest;
import com.ecommerce.cartService.dto.CartResponse;
import com.ecommerce.cartService.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping
	public ResponseEntity<String> addToCart(@RequestBody CartRequest cartRequest){
		return ResponseEntity.ok(cartService.addToCart(cartRequest));
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<CartResponse> getCart(@PathVariable String userId){
		return ResponseEntity.ok(cartService.getCart(userId));
	}

}
