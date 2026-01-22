package com.ecommerce.notificationService.dto;

import lombok.Data;

@Data
public class PaymentEvent {
    private String orderId;
    private String userId;
    private double amount;
    private String status;   
}

