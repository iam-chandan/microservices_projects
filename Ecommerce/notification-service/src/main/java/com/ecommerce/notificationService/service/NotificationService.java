package com.ecommerce.notificationService.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.ecommerce.notificationService.dto.PaymentEvent;
import com.ecommerce.notificationService.entity.NotificationEntity;
import com.ecommerce.notificationService.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public void sendNotification(PaymentEvent event) {

        String message = "Payment successful for Order " + event.getOrderId() +
                ", Amount: " + event.getAmount();

        // Simulate Email Sending
        System.out.println("📧 Email sent to user " + event.getUserId() + ": " + message);

        NotificationEntity notification = NotificationEntity.builder()
                .userId(event.getUserId())
                .orderId(event.getOrderId())
                .message(message)
                .status("SENT")
                .createdAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);
    }
}
