package com.ecommerce.notificationService.service;

import org.springframework.stereotype.Service;

import com.ecommerce.notificationService.dto.OrderEvent;
import com.ecommerce.notificationService.dto.PaymentEvent;

@Service
public class NotificationService {

    public void sendOrderCreatedNotification(OrderEvent event) {
        System.out.println("SMS/EMAIL: Order Created!");
        System.out.println("User: " + event.getUserId());
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Total: " + event.getTotalAmount());
    }

    public void sendPaymentSuccessNotification(PaymentEvent event) {
        System.out.println("SMS/EMAIL: Payment Successful!");
        System.out.println("User: " + event.getUserId());
        System.out.println("Order ID: " + event.getOrderId());
        System.out.println("Amount Paid: " + event.getAmount());
    }
}
