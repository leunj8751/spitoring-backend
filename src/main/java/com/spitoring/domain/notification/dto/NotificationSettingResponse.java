package com.spitoring.domain.notification.dto;

import com.spitoring.domain.notification.domain.NotificationSetting;
import com.spitoring.domain.spitto.domain.SpittoType;

import java.time.LocalDateTime;

public record NotificationSettingResponse(
    Long id,
    SpittoType spittoType,
    Integer releaseRate,
    Integer rnk1RemainingMin,
    Integer rnk2RemainingMin,
    LocalDateTime createdAt
) {
    public static NotificationSettingResponse from(NotificationSetting setting) {
        return new NotificationSettingResponse(
            setting.getId(),
            setting.getSpittoType(),
            setting.getReleaseRate(),
            setting.getRnk1RemainingMin(),
            setting.getRnk2RemainingMin(),
            setting.getCreatedAt()
        );
    }
}
