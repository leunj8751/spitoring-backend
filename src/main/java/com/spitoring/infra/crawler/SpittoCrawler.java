package com.spitoring.infra.crawler;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class SpittoCrawler {

    @Value("${app.crawler.spitto-url}")
    private String spittoUrl;

    public List<SpittoData> crawl() throws IOException {
        log.info("스피또 데이터 크롤링 시작");
        Document doc = Jsoup.connect(spittoUrl)
            .userAgent("Mozilla/5.0")
            .timeout(10_000)
            .get();

        // TODO: 실제 동행복권 페이지 HTML 구조에 맞게 파싱 로직 구현
        // doc.select("table.tbl_data tr") 등으로 파싱
        log.info("스피또 데이터 크롤링 완료");
        return List.of();
    }

    public record SpittoData(
        Integer gameId,
        String gameName,
        Integer price,
        Long remainingQuantity,
        Long firstPrizeRemaining
    ) {}
}
