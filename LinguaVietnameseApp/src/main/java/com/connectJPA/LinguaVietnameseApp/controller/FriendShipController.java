package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.FriendShipDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.FriendShipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/friends")
@RequiredArgsConstructor
@Tag(name = "Friend", description = "Friend API")
public class FriendShipController {

    private final FriendShipService friendService;

    @GetMapping("/requester/{userId}")
    @Operation(summary = "Get friends requested by user")
    public ResponseEntity<ApiResponse<List<FriendShipDTO>>> getFriendsRequestedBy(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<FriendShipDTO>>builder()
                .message("Success")
                .result(friendService.getByRequester(userId))
                .build());
    }
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get all friends of user")
    public ResponseEntity<ApiResponse<List<FriendShipDTO>>> getByUser(@PathVariable String userId) {
        return ResponseEntity.ok(ApiResponse.<List<FriendShipDTO>>builder()
                .message("Success")
                .result(friendService.getByUser(userId))
                .build());
    }

    @PostMapping
    @Operation(summary = "Create friend request")
    public ResponseEntity<ApiResponse<FriendShipDTO>> create(@RequestBody FriendShipDTO dto) {
        return ResponseEntity.ok(ApiResponse.<FriendShipDTO>builder()
                .message("Created")
                .result(friendService.create(dto))
                .build());
    }

    @PostMapping("/request")
    public ResponseEntity<?> sendFriendRequest(@RequestParam String senderId, @RequestParam String receiverId) {
        friendService.sendFriendRequest(senderId, receiverId);
        return ResponseEntity.ok("Đã gửi lời mời kết bạn");
    }

    @DeleteMapping("/{requesterId}/{receiverId}")
    @Operation(summary = "Delete friend relationship")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String requesterId, @PathVariable String receiverId) {
        friendService.delete(requesterId, receiverId);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Deleted").build());
    }
}
