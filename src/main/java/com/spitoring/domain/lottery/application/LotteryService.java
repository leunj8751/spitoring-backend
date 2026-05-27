package com.spitoring.domain.lottery.application;

import com.spitoring.domain.lottery.domain.SpittoInfoRepository;
import com.spitoring.domain.lottery.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LotteryService {

    private final SpittoInfoRepository spittoInfoRepository;

    public DashboardResponse getDashboard() {
        var items = spittoInfoRepository.findAllByOrderByGameIdAsc().stream()
            .map(DashboardResponse.SpittoItemDto::from)
            .toList();

        return DashboardResponse.builder()
            .items(items)
            .refreshedAt(LocalDateTime.now())
            .build();
    }
}
