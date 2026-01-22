package com.ecommerce.notificationService.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.notificationService.entity.NotificationEntity;

public interface NotificationRepository extends JpaRepository<NotificationEntity, Integer>{

}
