package com.ecommerce.paymentService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEntity {

    @Id
    private String paymentId;

    @Column(nullable = false)
    private String orderId;

    @Column(nullable = false)
    private String userId;

    @Column
    private double amount;

    @Column
    private String status;  

    @Column
    private LocalDateTime createdAt;
}

