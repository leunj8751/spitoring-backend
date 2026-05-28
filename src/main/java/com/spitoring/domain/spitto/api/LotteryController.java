package com.spitoring.domain.spitto.api;

import com.spitoring.common.response.ApiResponse;
import com.spitoring.domain.spitto.service.LotteryService;
import com.spitoring.domain.spitto.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/lottery")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryService lotteryService;

    @GetMapping("/crawl")
    public ApiResponse<String> crawl() throws Exception {
        int count = lotteryService.crawlAndSave();
        return ApiResponse.ok(count + "건 저장 완료");
    }

    @GetMapping("/dashboard")
    public ApiResponse<DashboardResponse> getDashboard() {
        return ApiResponse.ok(lotteryService.getDashboard());
    }
}
