package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.CoupleDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.CoupleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/couples")
@RequiredArgsConstructor
@Tag(name = "Couple", description = "Couple API")
public class CoupleController {

    private final CoupleService coupleService;

    @GetMapping
    @Operation(summary = "Get all couples")
    public ResponseEntity<ApiResponse<List<CoupleDTO>>> getAllCouples() {
        return ResponseEntity.ok(ApiResponse.<List<CoupleDTO>>builder()
                .message("Success")
                .result(coupleService.getAllCouples())
                .build());
    }
    @PostMapping
    @Operation(summary = "Create couple")
    public ResponseEntity<ApiResponse<CoupleDTO>> create(@RequestBody CoupleDTO dto) {
        return ResponseEntity.ok(ApiResponse.<CoupleDTO>builder()
                .message("Created")
                .result(coupleService.create(dto))
                .build());
    }

    @DeleteMapping("/{user1Id}/{user2Id}")
    @Operation(summary = "Delete couple")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String user1Id, @PathVariable String user2Id) {
        coupleService.delete(user1Id, user2Id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Deleted").build());
    }

    @GetMapping("/partner/{userId}")
    @Operation(summary = "Get current couple/partner of user")
    public ResponseEntity<ApiResponse<CoupleDTO>> getPartner(@PathVariable String userId) {
        return coupleService.getPartner(userId)
                .map(partner -> ResponseEntity.ok(ApiResponse.<CoupleDTO>builder()
                        .message("Success")
                        .result(partner)
                        .build()))
                .orElse(ResponseEntity.status(404).body(ApiResponse.<CoupleDTO>builder()
                        .code(404)
                        .message("No couple found")
                        .build()));
    }
}

