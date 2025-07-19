package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.LessonProgressDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.LessonProgressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/lessons-progress")
@RequiredArgsConstructor
@Tag(name = "Lesson progress", description = "Lesson progress Management API")
public class LessonProgressController {

    private final LessonProgressService lessonProgressService;

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get lesson progress by user")
    public ResponseEntity<ApiResponse<List<LessonProgressDTO>>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<LessonProgressDTO>>builder()
                .message("Success")
                .result(lessonProgressService.getByUser(userId))
                .build());
    }

    @PostMapping
    @Operation(summary = "Create lesson progress")
    public ResponseEntity<ApiResponse<LessonProgressDTO>> create(@RequestBody LessonProgressDTO dto) {
        return ResponseEntity.ok(ApiResponse.<LessonProgressDTO>builder()
                .message("Created")
                .result(lessonProgressService.create(dto))
                .build());
    }

}
