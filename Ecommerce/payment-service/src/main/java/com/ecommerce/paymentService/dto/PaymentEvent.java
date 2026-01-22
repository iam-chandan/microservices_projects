package com.ecommerce.paymentService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentEvent {
    private String paymentId;
    private String orderId;
    private String userId;
    private double amount;
    private String status;
    private LocalDateTime timestamp;
}

