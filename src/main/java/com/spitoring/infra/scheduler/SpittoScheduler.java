package com.spitoring.infra.scheduler;

import com.spitoring.infra.crawler.SpittoCrawler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class SpittoScheduler {

    private final SpittoCrawler crawler;

    /**
     * 스피또 데이터 수집 및 조건 충족 사용자 알림 발송
     * cron은 application.yml의 app.crawler.schedule-cron 으로 관리
     */
    @Scheduled(cron = "${app.crawler.schedule-cron}")
    public void checkAndNotify() {
        log.info("[Scheduler] 스피또 조건 검사 시작");
        try {
            var dataList = crawler.crawl();
            // TODO: 각 게임 데이터 DB 업데이트 후
            //       NotificationSetting 조건과 비교하여 FCM 발송
            log.info("[Scheduler] 스피또 조건 검사 완료 - {}건 처리", dataList.size());
        } catch (Exception e) {
            log.error("[Scheduler] 스피또 조건 검사 실패", e);
        }
    }
}
