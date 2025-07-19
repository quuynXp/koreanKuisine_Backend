package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.LeaderboardEntryDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.LeaderboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/leaderboards")
@RequiredArgsConstructor
public class LeaderboardController {

    private final LeaderboardService leaderboardService;

    @GetMapping("/top3")
    public ApiResponse<List<LeaderboardEntryDTO>> getTop3(
            @RequestParam String tab,
            @RequestParam String period) {
        return new ApiResponse<>(200, "OK", leaderboardService.getTop3(tab, period));
    }

    @GetMapping
    public ApiResponse<Page<LeaderboardEntryDTO>> getPaged(
            @RequestParam String tab,
            @RequestParam String period,
            @RequestParam int page,
            @RequestParam int size) {
        return new ApiResponse<>(200, "OK", leaderboardService.getPaged(tab, period, page, size));
    }

    @GetMapping("/focus")
    public ApiResponse<LeaderboardEntryDTO> getFocus(@RequestParam String userId,
                                                     @RequestParam String tab,
                                                     @RequestParam String period) {
        return new ApiResponse<>(200, "OK", leaderboardService.getFocusUserEntry(tab, period, userId));
    }

    @PostMapping("/snapshot")
    public ApiResponse<String> snapshot(@RequestParam String tab,
                                        @RequestParam String period) {
        leaderboardService.snapshotLeaderboard(tab, period);
        return new ApiResponse<>(200, "Snapshot created", null);
    }
}

