package com.spitoring.domain.notification.application;

import com.spitoring.domain.notification.domain.NotificationSetting;
import com.spitoring.domain.notification.domain.NotificationSettingRepository;
import com.spitoring.domain.notification.dto.NotificationSettingRequest;
import com.spitoring.domain.notification.dto.NotificationSettingResponse;
import com.spitoring.domain.spitto.domain.SpittoType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationSettingService {

    private final NotificationSettingRepository repository;

    @Transactional
    public Long save(NotificationSettingRequest request, String userIp) {
        NotificationSetting setting = NotificationSetting.builder()
            .spittoType(SpittoType.from(request.spittoType()))
            .releaseRate(request.releaseRate())
            .rnk1RemainingMin(request.rnk1RemainingMin())
            .rnk2RemainingMin(request.rnk2RemainingMin())
            .userIp(userIp)
            .build();

        return repository.save(setting).getId();
    }

    @Transactional(readOnly = true)
    public List<NotificationSettingResponse> getByUserIp(String userIp) {
        return repository.findByUserIp(userIp).stream()
            .map(NotificationSettingResponse::from)
            .toList();
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
