package com.spitoring.domain.notification.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationSettingRequest(

    @NotBlank(message = "스피또 종류는 필수입니다.")
    String spittoType,

    @NotNull(message = "입고율은 필수입니다.")
    @Min(value = 0, message = "입고율은 0 이상이어야 합니다.")
    @Max(value = 100, message = "입고율은 100 이하이어야 합니다.")
    Integer releaseRate,

    @Min(value = 0, message = "1등 잔여 수량은 0 이상이어야 합니다.")
    Integer rnk1RemainingMin,

    @Min(value = 0, message = "2등 잔여 수량은 0 이상이어야 합니다.")
    Integer rnk2RemainingMin
) {}
