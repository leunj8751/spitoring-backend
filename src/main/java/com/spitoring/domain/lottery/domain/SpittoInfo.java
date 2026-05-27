package com.spitoring.domain.lottery.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 동행복권 스피또 종류별 현재 재고 정보 스냅샷
 */
@Entity
@Table(name = "spitto_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpittoInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer gameId;              // 스피또 게임 번호 (1000, 2000 등)

    @Column(nullable = false)
    private String gameName;             // 예: 스피또 1000

    private Integer price;              // 1장 가격 (원)

    private Long totalQuantity;         // 총 발행 수량

    private Long remainingQuantity;     // 잔여 수량

    @Column(precision = 5, scale = 2)
    private BigDecimal stockRate;       // 입고율 (%)

    private Long firstPrizeRemaining;   // 1등 잔여 수량

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public void updateStock(Long remainingQuantity, BigDecimal stockRate, Long firstPrizeRemaining) {
        this.remainingQuantity = remainingQuantity;
        this.stockRate = stockRate;
        this.firstPrizeRemaining = firstPrizeRemaining;
    }
}
