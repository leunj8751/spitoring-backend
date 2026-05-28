package com.spitoring.infra.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spitoring.domain.spitto.domain.SpittoStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class SpittoCrawler {

    @Value("${app.crawler.spitto-url}")
    private String spittoUrl;

    private final HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<SpittoStock> crawl() throws Exception {
        log.info("스피또 데이터 수집 시작");

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(spittoUrl))
            .timeout(Duration.ofSeconds(10))
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
            .header("Accept", "application/json")
            .header("Referer", "https://www.dhlottery.co.kr/")
            .GET()
            .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new IllegalStateException("API 호출 실패 - 상태코드: " + response.statusCode());
        }

        JsonNode stList = objectMapper.readTree(response.body())
            .path("data").path("result").path("sellList").path("st");

        List<SpittoStock> result = new ArrayList<>();
        for (JsonNode item : stList) {
            long[] rnk1 = parseRank(item.path("stRnk1Rt").asText());
            long[] rnk2 = parseRank(item.path("stRnk2Rt").asText());
            long[] rnk3 = parseRank(item.path("stRnk3Rt").asText());

            result.add(SpittoStock.builder()
                .gameTypeCd(item.path("stGmTypeCd").asText())
                .gameTypeNm(item.path("stGmTypeNm").asText())
                .draw(item.path("stEpsd").asInt())
                .stockRate(item.path("stSpmtRt").asInt())
                .rnk1Remaining(rnk1[0])
                .rnk1Total(rnk1[1])
                .rnk2Remaining(rnk2[0])
                .rnk2Total(rnk2[1])
                .rnk3Remaining(rnk3[0])
                .rnk3Total(rnk3[1])
                .build());
        }

        log.info("[Crawler] 스피또 데이터 {}건 수집 완료", result.size());
        return result;
    }


    private long[] parseRank(String rankStr) {
        try {
            String[] parts = rankStr.replace("매", "").split("/");
            return new long[]{Long.parseLong(parts[0].trim()), Long.parseLong(parts[1].trim())};
        } catch (Exception e) {
            log.warn("[Crawler] 잔여수량 파싱 실패: '{}'", rankStr);
            return new long[]{0L, 0L};
        }
    }
}
