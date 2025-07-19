package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.RoomDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.RoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/rooms")
@RequiredArgsConstructor
@Tag(name = "Room", description = "Room Management API")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    @Operation(summary = "Get all rooms")
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getAllRooms() {
        return ResponseEntity.ok(ApiResponse.<List<RoomDTO>>builder()
                .message("Success")
                .result(roomService.getAllRooms())
                .build());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get room by ID")
    public ResponseEntity<ApiResponse<RoomDTO>> getRoomById(@PathVariable String id) {
        return roomService.getById(id)
                .map(room -> ResponseEntity.ok(ApiResponse.<RoomDTO>builder()
                        .message("Success")
                        .result(room)
                        .build()))
                .orElse(ResponseEntity.status(404).body(ApiResponse.<RoomDTO>builder()
                        .code(404)
                        .message("Room not found")
                        .build()));
    }

    @GetMapping("/creator/{creatorId}")
    @Operation(summary = "Get rooms by creator")
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getByCreator(@PathVariable String creatorId) {
        return ResponseEntity.ok(ApiResponse.<List<RoomDTO>>builder()
                .message("Success")
                .result(roomService.getByCreator(creatorId))
                .build());
    }

    @PostMapping
    @Operation(summary = "Create room")
    public ResponseEntity<ApiResponse<RoomDTO>> create(@RequestBody RoomDTO dto) {
        return ResponseEntity.ok(ApiResponse.<RoomDTO>builder()
                .message("Created")
                .result(roomService.create(dto))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update room")
    public ResponseEntity<ApiResponse<RoomDTO>> update(@PathVariable String id, @RequestBody RoomDTO dto) {
        return roomService.update(id, dto)
                .map(updated -> ResponseEntity.ok(ApiResponse.<RoomDTO>builder()
                        .message("Updated")
                        .result(updated)
                        .build()))
                .orElse(ResponseEntity.status(404).body(ApiResponse.<RoomDTO>builder()
                        .code(404)
                        .message("Room not found")
                        .build()));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete room")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        roomService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Deleted")
                .build());
    }

    @PostMapping("/{id}/members")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<Void>> addMember(@PathVariable String id, @RequestBody String userId) {
        roomService.addUserToRoom(id, userId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("User added").build());
    }

    @DeleteMapping("/{id}/members/{userId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<Void>> removeMember(@PathVariable String id, @PathVariable String userId) {
        roomService.removeUserFromRoom(id, userId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("User removed").build());
    }
    @GetMapping("/my")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<List<RoomDTO>>> getMyRooms(@AuthenticationPrincipal Jwt principal) {
        String userId = principal.getSubject();
        return ResponseEntity.ok(ApiResponse.<List<RoomDTO>>builder()
                .message("Success")
                .result(roomService.getRoomsByUser(userId))
                .build());
    }


}
