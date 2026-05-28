package com.spitoring.domain.spitto.service;

import com.spitoring.domain.spitto.domain.SpittoStockRepository;
import com.spitoring.domain.spitto.dto.DashboardResponse;
import com.spitoring.infra.crawler.SpittoCrawler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LotteryService {

    private final SpittoStockRepository spittoStockRepository;
    private final SpittoCrawler spittoCrawler;

    @Transactional
    public int crawlAndSave() throws Exception {
        var stocks = spittoCrawler.crawl();
        spittoStockRepository.saveAll(stocks);
        return stocks.size();
    }

    public DashboardResponse getDashboard() {
        var items = spittoStockRepository.findAllByOrderByGameTypeCdAscDrawDesc().stream()
            .map(DashboardResponse.SpittoItemDto::from)
            .toList();

        return DashboardResponse.builder()
            .items(items)
            .refreshedAt(LocalDateTime.now())
            .build();
    }
}
