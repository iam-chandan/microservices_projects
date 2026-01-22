package com.ecommerce.orderService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "tb_order_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItemEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String productId;
	
	@Column
	private int quantity;
	
	@Column
	private double price;
	
	@ManyToOne
	@JoinColumn(name = "order_id")
	private OrderEntity order;
}
