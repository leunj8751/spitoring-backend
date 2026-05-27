package com.spitoring.domain.notification.api;

import com.spitoring.common.response.ApiResponse;
import com.spitoring.domain.notification.application.NotificationSettingService;
import com.spitoring.domain.notification.domain.NotificationSetting;
import com.spitoring.domain.notification.dto.NotificationSettingRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications/settings")
@RequiredArgsConstructor
public class NotificationSettingController {

    private final NotificationSettingService notificationSettingService;

    @GetMapping
    public ApiResponse<List<NotificationSetting>> getSettings(@RequestParam Long userId) {
        return ApiResponse.ok(notificationSettingService.getSettings(userId));
    }

    @PutMapping
    public ApiResponse<NotificationSetting> upsertSetting(
        @RequestParam Long userId,
        @Valid @RequestBody NotificationSettingRequest request
    ) {
        return ApiResponse.ok(notificationSettingService.upsertSetting(userId, request));
    }

    @DeleteMapping("/{gameId}")
    public ApiResponse<Void> deleteSetting(@RequestParam Long userId, @PathVariable Integer gameId) {
        notificationSettingService.deleteSetting(userId, gameId);
        return ApiResponse.ok();
    }
}
