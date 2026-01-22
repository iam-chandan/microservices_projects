package com.ecommerce.orderService.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.orderService.dto.OrderItemRequest;
import com.ecommerce.orderService.entity.OrderEntity;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, String>{

	List<OrderEntity> findByUserId(String userId);

}
