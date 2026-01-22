package com.ecommerce.paymentService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.paymentService.entity.PaymentEntity;

public interface PaymentRepository extends JpaRepository<PaymentEntity, String> {
}

