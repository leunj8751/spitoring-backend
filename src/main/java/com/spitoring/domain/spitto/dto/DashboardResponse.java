package com.spitoring.domain.spitto.dto;

import com.spitoring.domain.spitto.domain.SpittoStock;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record DashboardResponse(
    List<SpittoItemDto> items,
    LocalDateTime refreshedAt
) {
    @Builder
    public record SpittoItemDto(
        String gameTypeCd,
        String gameTypeNm,
        Integer draw,
        Integer stockRate,
        Long rnk1Remaining,
        Long rnk1Total,
        Long rnk2Remaining,
        Long rnk2Total,
        Long rnk3Remaining,
        Long rnk3Total
    ) {
        public static SpittoItemDto from(SpittoStock stock) {
            return new SpittoItemDto(
                stock.getGameTypeCd(),
                stock.getGameTypeNm(),
                stock.getDraw(),
                stock.getStockRate(),
                stock.getRnk1Remaining(),
                stock.getRnk1Total(),
                stock.getRnk2Remaining(),
                stock.getRnk2Total(),
                stock.getRnk3Remaining(),
                stock.getRnk3Total()
            );
        }
    }
}
