package com.ecommerce.paymentService.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecommerce.paymentService.dto.PaymentEvent;
import com.ecommerce.paymentService.dto.PaymentRequest;
import com.ecommerce.paymentService.dto.PaymentResponse;
import com.ecommerce.paymentService.entity.PaymentEntity;
import com.ecommerce.paymentService.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final KafkaTemplate<String, PaymentEvent> kafkaTemplate;

    private static final String TOPIC = "payment-events";

    @Transactional
    public PaymentResponse makePayment(PaymentRequest request) {

        String paymentId = UUID.randomUUID().toString();

        PaymentEntity payment = PaymentEntity.builder()
                .paymentId(paymentId)
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status("SUCCESS")   // For now we assume success
                .createdAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        PaymentEvent event = PaymentEvent.builder()
                .paymentId(paymentId)
                .orderId(request.getOrderId())
                .userId(request.getUserId())
                .amount(request.getAmount())
                .status("SUCCESS")
                .timestamp(LocalDateTime.now())
                .build();

        kafkaTemplate.send(TOPIC, event);

        return PaymentResponse.builder()
                .paymentId(paymentId)
                .status("SUCCESS")
                .build();
    }
}

