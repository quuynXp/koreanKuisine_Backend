package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.GroupSessionDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.GroupSessionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/group-sessions")
@RequiredArgsConstructor
@Tag(name = "Group Session", description = "Group study sessions")
public class GroupSessionController {

    private final GroupSessionService groupSessionService;

    @GetMapping("/room/{roomId}")
    @Operation(summary = "Get all sessions in a room")
    public ResponseEntity<ApiResponse<List<GroupSessionDTO>>> getByRoom(@PathVariable String roomId) {
        return ResponseEntity.ok(ApiResponse.<List<GroupSessionDTO>>builder()
                .message("Success")
                .result(groupSessionService.getByRoomId(roomId))
                .build());
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all sessions of a user")
    public ResponseEntity<ApiResponse<List<GroupSessionDTO>>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<GroupSessionDTO>>builder()
                .message("Success")
                .result(groupSessionService.getByUserId(userId))
                .build());
    }

    @PostMapping
    @Operation(summary = "Create new session")
    public ResponseEntity<ApiResponse<GroupSessionDTO>> create(@RequestBody GroupSessionDTO dto) {
        return ResponseEntity.ok(ApiResponse.<GroupSessionDTO>builder()
                .message("Created")
                .result(groupSessionService.create(dto))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update session")
    public ResponseEntity<ApiResponse<GroupSessionDTO>> update(@PathVariable String id, @RequestBody GroupSessionDTO dto) {
        return groupSessionService.update(id, dto)
                .map(updated -> ResponseEntity.ok(ApiResponse.<GroupSessionDTO>builder()
                        .message("Updated")
                        .result(updated)
                        .build()))
                .orElse(ResponseEntity.status(404).body(ApiResponse.<GroupSessionDTO>builder()
                        .code(404)
                        .message("Session not found")
                        .build()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete session")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        groupSessionService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Deleted")
                .build());
    }
}
