package com.ecommerce.orderService.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse {
	private String orderId;
	private String userId;
	private double totalAmount;
	private String status;
	private List<OrderItemResponse> items;
}
