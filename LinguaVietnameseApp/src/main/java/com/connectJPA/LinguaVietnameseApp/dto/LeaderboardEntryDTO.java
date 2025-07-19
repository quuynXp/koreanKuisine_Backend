package com.connectJPA.LinguaVietnameseApp.dto;


import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LeaderboardEntryDTO {
    private String leaderboardEntryId;
    private String userId;
    private String name;
    private String fullName;
    private String avatarUrl;
    private int score;
    private int level;
    private int streak;
    private int rank;
    private int change;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}

