package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.LessonDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/lessons")
@RequiredArgsConstructor
@Tag(name = "Lesson", description = "Lesson Management API")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    @Operation(summary = "Get all lessons")
    public ResponseEntity<ApiResponse<List<LessonDTO>>> getAllLessons() {
        return ResponseEntity.ok(ApiResponse.<List<LessonDTO>>builder()
                .message("Success")
                .result(lessonService.getAllLessons())
                .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get lesson by ID")
    public ResponseEntity<ApiResponse<LessonDTO>> getLessonById(@PathVariable String id) {
        return lessonService.getById(id)
                .map(lesson -> ResponseEntity.ok(ApiResponse.<LessonDTO>builder()
                        .message("Success")
                        .result(lesson)
                        .build()))
                .orElse(ResponseEntity.status(404).body(ApiResponse.<LessonDTO>builder()
                        .code(404)
                        .message("Lesson not found")
                        .build()));
    }
    @PostMapping
    @Operation(summary = "Create lesson")
    public ResponseEntity<ApiResponse<LessonDTO>> create(@RequestBody LessonDTO dto) {
        return ResponseEntity.ok(ApiResponse.<LessonDTO>builder()
                .message("Created")
                .result(lessonService.create(dto))
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete lesson")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        lessonService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Deleted").build());
    }

}
