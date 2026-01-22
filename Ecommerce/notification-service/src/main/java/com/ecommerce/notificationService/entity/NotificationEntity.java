package com.ecommerce.notificationService.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_notifications")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String userId;
    private String orderId;
    private String message;
    private String status;   
    private LocalDateTime createdAt;
}
