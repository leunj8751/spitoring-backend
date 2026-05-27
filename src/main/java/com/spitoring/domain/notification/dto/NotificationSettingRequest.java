package com.spitoring.domain.notification.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record NotificationSettingRequest(

    @NotNull(message = "게임 번호는 필수입니다.")
    Integer gameId,

    @DecimalMin(value = "0.0", message = "입고율은 0 이상이어야 합니다.")
    @DecimalMax(value = "100.0", message = "입고율은 100 이하이어야 합니다.")
    BigDecimal minStockRate,

    @Min(value = 0, message = "1등 잔여 수량은 0 이상이어야 합니다.")
    Long minFirstPrize,

    boolean enabled
) {}
