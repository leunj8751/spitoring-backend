package com.spitoring.domain.spitto.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "spitto_stock",
    indexes = {
        @Index(name = "idx_game_episode", columnList = "game_type_cd, episode"),
        @Index(name = "idx_inserted_at",  columnList = "inserted_at")
    }
)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class SpittoStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "game_type_cd", nullable = false, length = 10)
    private String gameTypeCd;       // 스피또 종류 코드 (SP500 / SP1000 / SP2000)

    @Column(name = "game_type_nm", nullable = false, length = 20)
    private String gameTypeNm;       // 스피또 종류명

    @Column(name = "draw", nullable = false)
    private Integer draw;            // 회차

    @Column(name = "stock_rate", nullable = false)
    private Integer stockRate;       // 판매점 입고율 (%)

    @Column(name = "rnk1_remaining", nullable = false)
    private Long rnk1Remaining;      // 1등 잔여 수량

    @Column(name = "rnk1_total", nullable = false)
    private Long rnk1Total;          // 1등 전체 발행 수량

    @Column(name = "rnk2_remaining", nullable = false)
    private Long rnk2Remaining;      // 2등 잔여 수량

    @Column(name = "rnk2_total", nullable = false)
    private Long rnk2Total;          // 2등 전체 발행 수량

    @Column(name = "rnk3_remaining", nullable = false)
    private Long rnk3Remaining;      // 3등 잔여 수량

    @Column(name = "rnk3_total", nullable = false)
    private Long rnk3Total;          // 3등 전체 발행 수량

    @CreationTimestamp
    @Column(name = "inserted_at", nullable = false, updatable = false)
    private LocalDateTime insertedAt;
}
