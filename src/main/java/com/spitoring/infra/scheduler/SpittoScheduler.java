package com.spitoring.infra.scheduler;

import com.spitoring.domain.spitto.domain.SpittoStockRepository;
import com.spitoring.infra.crawler.SpittoCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpittoScheduler {

    private final SpittoCrawler crawler;
    private final SpittoStockRepository spittoStockRepository;

    @Scheduled(cron = "${app.crawler.schedule-cron}")
    @Transactional
    public void collectAndSave() {
        log.info("[Scheduler] 스피또 데이터 수집 시작");
        try {
            var stocks = crawler.crawl();
            if (stocks.isEmpty()) {
                log.warn("[Scheduler] 수집된 데이터가 없습니다");
                return;
            }
            spittoStockRepository.saveAll(stocks);
            log.info("[Scheduler] 스피또 데이터 {}건 저장 완료", stocks.size());
        } catch (Exception e) {
            log.error("[Scheduler] 스피또 데이터 수집/저장 실패", e);
        }
    }
}
