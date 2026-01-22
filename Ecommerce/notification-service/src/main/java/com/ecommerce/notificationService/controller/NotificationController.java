package com.ecommerce.notificationService.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.ecommerce.notificationService.entity.NotificationEntity;
import com.ecommerce.notificationService.repository.NotificationRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationRepository notificationRepository;

    @GetMapping
    public List<NotificationEntity> getAllNotifications() {
        return notificationRepository.findAll();
    }
}
