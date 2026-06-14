package com.spitoring.domain.notification.domain;

import com.spitoring.domain.spitto.domain.SpittoType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification_setting")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "spitto_type", nullable = false, length = 10)
    private SpittoType spittoType;

    @Column(name = "release_rate", nullable = false)
    private Integer releaseRate;

    @Column(name = "rnk1_remaining_min", nullable = false)
    private Integer rnk1RemainingMin;

    @Column(name = "rnk2_remaining_min", nullable = false)
    private Integer rnk2RemainingMin;

    @Column(name = "user_ip", nullable = false, length = 45)
    private String userIp;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
