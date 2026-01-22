package com.ecommerce.orderService.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.orderService.client.CartClient;
import com.ecommerce.orderService.dto.CartResponse;
import com.ecommerce.orderService.dto.OrderItemResponse;
import com.ecommerce.orderService.dto.OrderRequest;
import com.ecommerce.orderService.dto.OrderResponse;
import com.ecommerce.orderService.entity.OrderEntity;
import com.ecommerce.orderService.entity.OrderItemEntity;
import com.ecommerce.orderService.repository.OrderItemRepository;
import com.ecommerce.orderService.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;
	private final OrderItemRepository orderItemRepository;
	private final CartClient cartClient;

	@Transactional
	public String createOrder(OrderRequest request) {

		String orderId = UUID.randomUUID().toString();

		double total = request.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

		OrderEntity order = OrderEntity.builder().orderId(orderId).userId(request.getUserId()).totalAmount(total)
				.status("CREATED").createdAt(LocalDateTime.now()).build();

		List<OrderItemEntity> items = request.getItems().stream().map(i -> OrderItemEntity.builder()
				.productId(i.getProductId()).quantity(i.getQuantity()).price(i.getPrice()).order(order).build())
				.toList();

		order.setItems(items);

		orderRepository.save(order);
		return orderId;
	}

	public OrderResponse getOrderById(String orderId) {
		OrderEntity order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found"));

		return OrderResponse.builder().orderId(order.getOrderId()).userId(order.getUserId())
				.totalAmount(order.getTotalAmount()).status(order.getStatus())
				.items(order.getItems().stream()
						.map(i -> new OrderItemResponse(i.getProductId(), i.getQuantity(), i.getPrice())).toList())
				.build();
	}

	public List<OrderResponse> getOrdersByUser(String userId) {
		return orderRepository.findByUserId(userId).stream().map(o -> OrderResponse.builder().orderId(o.getOrderId())
				.userId(o.getUserId()).totalAmount(o.getTotalAmount()).status(o.getStatus())
				.items(o.getItems().stream()
						.map(i -> new OrderItemResponse(i.getProductId(), i.getQuantity(), i.getPrice())).toList())
				.build()).toList();
	}

	@Transactional
	public String createOrderFromCart(String userId) {

		CartResponse cart = cartClient.getCart(userId);

		if (cart.getItems().isEmpty()) {
			throw new RuntimeException("Cart is empty");
		}

		String orderId = UUID.randomUUID().toString();

		double total = cart.getItems().stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();

		OrderEntity order = OrderEntity.builder().orderId(orderId).userId(userId).totalAmount(total).status("CREATED")
				.createdAt(LocalDateTime.now()).build();

		List<OrderItemEntity> items = cart.getItems().stream().map(i -> OrderItemEntity.builder()
				.productId(i.getProductId()).quantity(i.getQuantity()).price(i.getPrice()).order(order).build())
				.toList();

		order.setItems(items);
		orderRepository.save(order);

		// Clear cart after successful order
		cartClient.clearCart(userId);

		return orderId;
	}

	@Transactional
	public void updateOrderStatus(String orderId, String status) {
	    OrderEntity order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new RuntimeException("Order not found"));

	    order.setStatus(status);
	    orderRepository.save(order);
	}


}
