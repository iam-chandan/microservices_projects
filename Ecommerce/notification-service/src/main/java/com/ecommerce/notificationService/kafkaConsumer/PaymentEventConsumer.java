package com.ecommerce.notificationService.kafkaConsumer;

import com.ecommerce.notificationService.dto.PaymentEvent;
import com.ecommerce.notificationService.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class PaymentEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentEventConsumer(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "payment-success-topic", groupId = "notification-group")
    public void consume(String message) {
        try {
            PaymentEvent event = objectMapper.readValue(message, PaymentEvent.class);
            notificationService.sendNotification(event);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

