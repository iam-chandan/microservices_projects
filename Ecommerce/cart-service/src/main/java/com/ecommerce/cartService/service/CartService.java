package com.ecommerce.cartService.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.cartService.dto.CartItemResponse;
import com.ecommerce.cartService.dto.CartRequest;
import com.ecommerce.cartService.dto.CartResponse;
import com.ecommerce.cartService.entity.CartEntity;
import com.ecommerce.cartService.entity.CartItemEntity;
import com.ecommerce.cartService.repository.CartItemRepository;
import com.ecommerce.cartService.repository.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Transactional
	public String addToCart(CartRequest cartRequest) {
		CartEntity cart = cartRepository.findByUserId(cartRequest.getUserId())
				.orElseGet(() -> cartRepository.save(CartEntity.builder().userId(cartRequest.getUserId())
						.items(new ArrayList<CartItemEntity>()).createdAt(LocalDateTime.now()).build()));

		Optional<CartItemEntity> existingItem = cart.getItems().stream()
				.filter(i -> i.getProductId().equals(cartRequest.getProductId())).findFirst();

		if (existingItem.isPresent()) {
			CartItemEntity item = existingItem.get();
			int updateQty = item.getQuantity() + cartRequest.getQuentity();
			if (updateQty <= 0) {
				cart.getItems().remove(item);
				cartItemRepository.delete(item);
			} else {
				item.setQuantity(updateQty);
			}
		} else {
			if (cartRequest.getQuentity() > 0) {
				CartItemEntity item = CartItemEntity.builder().productId(cartRequest.getProductId())
						.quantity(cartRequest.getQuentity()).price(cartRequest.getPrice()).cart(cart).build();
				cart.getItems().add(item);
			}
		}

		cartRepository.save(cart);
		return "Item added to cart";
	}

	public CartResponse getCart(String userId) {
		CartEntity cart = cartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Cart not found exception"));

		List<CartItemResponse> items = cart.getItems().stream()
				.map(item -> new CartItemResponse(item.getProductId(), item.getQuantity(), item.getPrice())).toList();
		return CartResponse.builder().userId(cart.getUserId()).items(items).build();
	}

}
