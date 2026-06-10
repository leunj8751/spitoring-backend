package com.spitoring.domain.spitto.service;

import com.spitoring.domain.spitto.domain.SpittoStockRepository;
import com.spitoring.domain.spitto.dto.DashboardResponse;
import com.spitoring.infra.crawler.SpittoCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LotteryService {

    private final SpittoStockRepository spittoStockRepository;

    public DashboardResponse getDashboard() {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);

        var items = spittoStockRepository
            .findAllByInsertedAtBetweenOrderByGameTypeCdAscDrawDesc(startOfDay, endOfDay)
            .stream()
            .map(DashboardResponse.SpittoItemDto::from)
            .toList();

        return DashboardResponse.builder()
            .items(items)
            .refreshedAt(LocalDateTime.now())
            .build();
    }
}
