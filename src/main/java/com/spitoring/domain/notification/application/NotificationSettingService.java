package com.spitoring.domain.notification.application;

import com.spitoring.domain.notification.domain.NotificationSetting;
import com.spitoring.domain.notification.domain.NotificationSettingRepository;
import com.spitoring.domain.notification.dto.NotificationSettingRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NotificationSettingService {

    private final NotificationSettingRepository repository;

    public List<NotificationSetting> getSettings(Long userId) {
        return repository.findByUserIdAndEnabledTrue(userId);
    }

    @Transactional
    public NotificationSetting upsertSetting(Long userId, NotificationSettingRequest request) {
        return repository.findByUserIdAndGameId(userId, request.gameId())
            .map(existing -> {
                existing.update(request.minStockRate(), request.minFirstPrize(), request.enabled());
                return existing;
            })
            .orElseGet(() -> repository.save(NotificationSetting.builder()
                .userId(userId)
                .gameId(request.gameId())
                .minStockRate(request.minStockRate())
                .minFirstPrize(request.minFirstPrize())
                .enabled(request.enabled())
                .build()));
    }

    @Transactional
    public void deleteSetting(Long userId, Integer gameId) {
        repository.findByUserIdAndGameId(userId, gameId)
            .ifPresent(repository::delete);
    }
}
