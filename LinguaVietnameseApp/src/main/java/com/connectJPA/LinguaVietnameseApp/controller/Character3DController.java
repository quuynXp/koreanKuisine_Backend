package com.connectJPA.LinguaVietnameseApp.controller;

import com.connectJPA.LinguaVietnameseApp.dto.Character3DDTO;
import com.connectJPA.LinguaVietnameseApp.dto.response.ApiResponse;
import com.connectJPA.LinguaVietnameseApp.service.Character3DService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/api/characters")
@RequiredArgsConstructor
@Tag(name = "Character", description = "3D Character API")
public class Character3DController {

    private final Character3DService characterService;

    @GetMapping
    @Operation(summary = "Get all 3D characters")
    public ResponseEntity<ApiResponse<List<Character3DDTO>>> getAllCharacters() {
        return ResponseEntity.ok(ApiResponse.<List<Character3DDTO>>builder()
                .message("Success")
                .result(characterService.getAllCharacters())
                .build());
    }

    @PostMapping
    @Operation(summary = "Create 3D character")
    public ResponseEntity<ApiResponse<Character3DDTO>> create(@RequestBody Character3DDTO dto) {
        return ResponseEntity.ok(ApiResponse.<Character3DDTO>builder()
                .message("Created")
                .result(characterService.create(dto))
                .build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update 3D character")
    public ResponseEntity<ApiResponse<Character3DDTO>> update(@PathVariable String id, @RequestBody Character3DDTO dto) {
        return ResponseEntity.ok(ApiResponse.<Character3DDTO>builder()
                .message("Updated")
                .result(characterService.update(id, dto))
                .build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete 3D character")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        characterService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder().message("Deleted").build());
    }
}
