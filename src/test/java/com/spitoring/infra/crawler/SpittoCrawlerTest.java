package com.spitoring.infra.crawler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

@Slf4j
class SpittoCrawlerTest {

    private static final String MAIN_INFO_URL = "https://www.dhlottery.co.kr/selectMainInfo.do";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void 동행복권_스피또_데이터_조회() throws Exception {
        HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(MAIN_INFO_URL))
            .timeout(Duration.ofSeconds(10))
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
            .header("Accept", "application/json")
            .header("Referer", "https://www.dhlottery.co.kr/")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        log.info("HTTP 상태코드: {}", response.statusCode());

        JsonNode root = objectMapper.readTree(response.body());
        JsonNode stList = root.path("data").path("result").path("sellList").path("st");

        log.info("============ 스피또 판매 현황 ============");
        for (JsonNode item : stList) {
            log.info(
                "[{}] 회차={} | 입고율={}% | 1등={} | 2등={} | 3등={}",
                item.path("stGmTypeCd").asText(),
                item.path("stEpsd").asInt(),
                item.path("stSpmtRt").asInt(),
                item.path("stRnk1Rt").asText(),
                item.path("stRnk2Rt").asText(),
                item.path("stRnk3Rt").asText()
            );
        }
        log.info("==========================================");
    }
}
