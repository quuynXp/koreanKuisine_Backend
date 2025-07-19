package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.BadgeDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.BadgeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/badges")
@RequiredArgsConstructor
@Tag(name = "Badge", description = "User badge list")
public class BadgeController {

    private final BadgeService badgeService;

    @GetMapping
    @Operation(summary = "Get all badges")
    public ResponseEntity<ApiResponse<List<BadgeDTO>>> getAllBadges() {
        return ResponseEntity.ok(ApiResponse.<List<BadgeDTO>>builder()
                .message("Success")
                .result(badgeService.getAllBadges())
                .build());
    }
    @PostMapping
    @Operation(summary = "Create badge")
    public ResponseEntity<ApiResponse<BadgeDTO>> create(@RequestBody BadgeDTO dto) {
        return ResponseEntity.ok(ApiResponse.<BadgeDTO>builder()
                .message("Created")
                .result(badgeService.create(dto))
                .build());
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get badges of a user")
    public ResponseEntity<ApiResponse<List<BadgeDTO>>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<BadgeDTO>>builder()
                .message("Success")
                .result(badgeService.getByUserId(userId))
                .build());
    }

    @PutMapping("/set-default/{userId}/{badgeId}")
    @Operation(summary = "Set default badge for user")
    public ResponseEntity<ApiResponse<Void>> setDefault(@PathVariable String userId, @PathVariable String badgeId) {
        badgeService.setDefaultBadge(userId, badgeId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Default badge updated").build());
    }

    @PutMapping("/{badgeId}")
    @Operation(summary = "Update badge")
    public ResponseEntity<ApiResponse<BadgeDTO>> update(@PathVariable String badgeId, @RequestBody BadgeDTO dto) {
        return ResponseEntity.ok(ApiResponse.<BadgeDTO>builder()
                .message("Updated")
                .result(badgeService.update(badgeId, dto))
                .build());
    }

    @DeleteMapping("/{badgeId}")
    @Operation(summary = "Delete badge")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String badgeId) {
        badgeService.delete(badgeId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Deleted").build());
    }
}
