package com.spitoring.domain.notification.domain;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * 사용자별 알림 조건 설정
 */
@Entity
@Table(name = "notification_settings",
    uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "game_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class NotificationSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Column(nullable = false)
    private Integer gameId;             // 알림 대상 스피또 게임 번호

    @Column(precision = 5, scale = 2)
    private BigDecimal minStockRate;    // 최소 입고율 조건 (%)

    private Long minFirstPrize;         // 최소 1등 잔여 수량 조건

    @Builder.Default
    private boolean enabled = true;

    public void update(BigDecimal minStockRate, Long minFirstPrize, boolean enabled) {
        this.minStockRate = minStockRate;
        this.minFirstPrize = minFirstPrize;
        this.enabled = enabled;
    }
}
