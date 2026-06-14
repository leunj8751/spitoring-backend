package com.spitoring.domain.spitto.domain;

import jakarta.persistence.*;
import com.spitoring.domain.spitto.domain.SpittoType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(
    name = "spitto_stock",
    indexes = {
        @Index(name = "idx_spitto_episode", columnList = "spitto_type, draw"),
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

    @Enumerated(EnumType.STRING)
    @Column(name = "spitto_type", nullable = false, length = 10)
    private SpittoType spittoType;

    @Column(name = "game_type_nm", nullable = false, length = 20)
    private String gameTypeNm;       // 스피또 종류명

    @Column(name = "draw", nullable = false)
    private Integer draw;            // 회차

    @Column(name = "release_rate", nullable = false)
    private Integer releaseRate;      // 판매점 입고율 (%)

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

    @Column(name = "pblcn_qty", nullable = false)
    private Long pblcnQty;           // 발행량

    @CreationTimestamp
    @Column(name = "inserted_at", nullable = false, updatable = false)
    private LocalDateTime insertedAt;
}
