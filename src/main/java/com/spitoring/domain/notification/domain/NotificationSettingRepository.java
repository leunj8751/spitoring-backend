package com.spitoring.domain.notification.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotificationSettingRepository extends JpaRepository<NotificationSetting, Long> {

    List<NotificationSetting> findByUserIdAndEnabledTrue(Long userId);

    Optional<NotificationSetting> findByUserIdAndGameId(Long userId, Integer gameId);

    // 특정 게임에 조건이 활성화된 모든 사용자 설정 조회 (스케줄러에서 사용)
    @Query("SELECT ns FROM NotificationSetting ns WHERE ns.gameId = :gameId AND ns.enabled = true")
    List<NotificationSetting> findActiveSettingsByGameId(Integer gameId);
}
