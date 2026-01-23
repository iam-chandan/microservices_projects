package com.ecommerce.notificationService.kafka;

import com.ecommerce.notificationService.dto.OrderEvent;
import com.ecommerce.notificationService.dto.PaymentEvent;
import com.ecommerce.notificationService.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper mapper = new ObjectMapper();

    public NotificationConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "order-created-topic", groupId = "notification-group")
    public void consumeOrderCreated(String message) {
        try {
            OrderEvent event = mapper.readValue(message, OrderEvent.class);
            notificationService.sendOrderCreatedNotification(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @KafkaListener(topics = "payment-success-topic", groupId = "notification-group")
    public void consumePaymentSuccess(String message) {
        try {
            PaymentEvent event = mapper.readValue(message, PaymentEvent.class);
            notificationService.sendPaymentSuccessNotification(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

