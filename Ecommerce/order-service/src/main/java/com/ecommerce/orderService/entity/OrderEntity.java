package com.ecommerce.orderService.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
	
	@Id
	private String orderId;
	
	@Column
	private String userId;
	
	@Column
	private double totalAmount;
	
	@Column
	private String status;
	
	@Column
	private LocalDateTime createdAt;
	
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItemEntity> items;
}
