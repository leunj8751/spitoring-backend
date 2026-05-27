package com.spitoring.domain.lottery.dto;

import com.spitoring.domain.lottery.domain.SpittoInfo;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DashboardResponse(
    List<SpittoItemDto> items,
    LocalDateTime refreshedAt
) {
    @Builder
    public record SpittoItemDto(
        Integer gameId,
        String gameName,
        Integer price,
        Long remainingQuantity,
        BigDecimal stockRate,
        Long firstPrizeRemaining
    ) {
        public static SpittoItemDto from(SpittoInfo info) {
            return new SpittoItemDto(
                info.getGameId(),
                info.getGameName(),
                info.getPrice(),
                info.getRemainingQuantity(),
                info.getStockRate(),
                info.getFirstPrizeRemaining()
            );
        }
    }
}
