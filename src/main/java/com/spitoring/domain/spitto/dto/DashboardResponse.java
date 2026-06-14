package com.spitoring.domain.spitto.dto;

import com.spitoring.domain.spitto.domain.SpittoStock;
import com.spitoring.domain.spitto.domain.SpittoType;
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
        SpittoType spittoType,
        String gameTypeNm,
        Integer draw,
        Integer releaseRate,
        Long pblcnQty,
        Long rnk1Remaining,
        Long rnk1Total,
        Long rnk2Remaining,
        Long rnk2Total,
        Long rnk3Remaining,
        Long rnk3Total,
        Double rnk1AvgWinProbability,
        Double rnk1CurrentWinProbability,
        Double rnk2AvgWinProbability,
        Double rnk2CurrentWinProbability
    ) {
        public static SpittoItemDto from(SpittoStock stock) {
            long circulatingQty = Math.round(stock.getPblcnQty() * stock.getReleaseRate() / 100.0);

            return new SpittoItemDto(
                stock.getSpittoType(),
                stock.getGameTypeNm(),
                stock.getDraw(),
                stock.getReleaseRate(),
                stock.getPblcnQty(),
                stock.getRnk1Remaining(),
                stock.getRnk1Total(),
                stock.getRnk2Remaining(),
                stock.getRnk2Total(),
                stock.getRnk3Remaining(),
                stock.getRnk3Total(),
                calcProbability(stock.getRnk1Total(), stock.getPblcnQty()),
                calcProbability(stock.getRnk1Remaining(), circulatingQty),
                calcProbability(stock.getRnk2Total(), stock.getPblcnQty()),
                calcProbability(stock.getRnk2Remaining(), circulatingQty)
            );
        }

        private static double calcProbability(long numerator, long denominator) {
            return denominator > 0 ? (double) numerator / denominator * 100 : 0.0;
        }
    }
}
