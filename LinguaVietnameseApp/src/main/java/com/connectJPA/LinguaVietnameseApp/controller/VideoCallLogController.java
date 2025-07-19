package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallLogDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.VideoCallLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/video-calls")
@RequiredArgsConstructor
@Tag(name = "Video Call", description = "Video Call History")
public class VideoCallLogController {

    private final VideoCallLogService videoCallLogService;

    @GetMapping("/caller/{callerId}")
    @Operation(summary = "Get all video calls by caller")
    public ResponseEntity<ApiResponse<List<VideoCallLogDTO>>> getByCaller(@PathVariable String callerId) {
        return ResponseEntity.ok(ApiResponse.<List<VideoCallLogDTO>>builder()
                .message("Success")
                .result(videoCallLogService.getByCaller(callerId))
                .build());
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all video calls of user")
    public ResponseEntity<ApiResponse<List<VideoCallLogDTO>>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<VideoCallLogDTO>>builder()
                .message("Success")
                .result(videoCallLogService.getByUser(userId))
                .build());
    }

    @PostMapping
    @Operation(summary = "Log a video call")
    public ResponseEntity<ApiResponse<VideoCallLogDTO>> create(@RequestBody VideoCallLogDTO dto) {
        return ResponseEntity.ok(ApiResponse.<VideoCallLogDTO>builder()
                .message("Created")
                .result(videoCallLogService.create(dto))
                .build());
    }
}
