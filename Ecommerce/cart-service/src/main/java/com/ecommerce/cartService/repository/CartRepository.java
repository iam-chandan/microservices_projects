package com.ecommerce.cartService.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.cartService.entity.CartEntity;

@Repository
public interface CartRepository extends JpaRepository<CartEntity, Integer>{

	Optional<CartEntity> findByUserId(String userId);

}
