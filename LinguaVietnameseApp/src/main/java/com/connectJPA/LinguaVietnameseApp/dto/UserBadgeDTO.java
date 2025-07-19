package com.connectJPA.LinguaVietnameseApp.dto;

import com.connectJPA.LinguaVietnameseApp.entity.id.UserBadgeId;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserBadgeDTO {
    private UserBadgeId userBadgeId;
    private LocalDateTime awardedAt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
