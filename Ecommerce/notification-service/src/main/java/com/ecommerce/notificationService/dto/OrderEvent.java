package com.ecommerce.notificationService.dto;

import lombok.Data;

@Data
public class OrderEvent {
    private String orderId;
    private String userId;
    private double totalAmount;
    private String status;  
}

