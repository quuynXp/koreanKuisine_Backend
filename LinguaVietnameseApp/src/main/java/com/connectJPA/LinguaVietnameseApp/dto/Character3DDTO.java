package com.connectJPA.LinguaVietnameseApp.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character3DDTO {
    private String character3DName;
    private String name;
    private String description;
    private String modelUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
