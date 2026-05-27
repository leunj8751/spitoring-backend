package com.spitoring.domain.lottery.api;

import com.spitoring.common.response.ApiResponse;
import com.spitoring.domain.lottery.application.LotteryService;
import com.spitoring.domain.lottery.dto.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/lottery")
@RequiredArgsConstructor
public class LotteryController {

    private final LotteryService lotteryService;

    @GetMapping("/dashboard")
    public ApiResponse<DashboardResponse> getDashboard() {
        return ApiResponse.ok(lotteryService.getDashboard());
    }
}
