package com.spitoring.domain.notification.api;

import com.spitoring.common.response.ApiResponse;
import com.spitoring.domain.notification.application.NotificationSettingService;
import com.spitoring.domain.notification.dto.NotificationSettingRequest;
import com.spitoring.domain.notification.dto.NotificationSettingResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
public class NotificationSettingController {

    private final NotificationSettingService notificationSettingService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<Long> save(@RequestBody @Valid NotificationSettingRequest request,
      HttpServletRequest httpRequest) {
        String userIp = resolveClientIp(httpRequest);
        return ApiResponse.ok(notificationSettingService.save(request, userIp));
    }

    @GetMapping
    public ApiResponse<List<NotificationSettingResponse>> getMySettings(HttpServletRequest httpRequest) {
        String userIp = resolveClientIp(httpRequest);
        return ApiResponse.ok(notificationSettingService.getByUserIp(userIp));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        notificationSettingService.delete(id);
        return ApiResponse.ok();
    }

    private String resolveClientIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !ip.isBlank()) {
            return ip.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }





}
