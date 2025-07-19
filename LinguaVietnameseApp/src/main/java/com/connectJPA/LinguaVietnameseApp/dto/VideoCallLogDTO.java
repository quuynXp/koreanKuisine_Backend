package com.connectJPA.LinguaVietnameseApp.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoCallLogDTO {
    private String videoCallId;
    private String callerId;
    private String calleeId;
    private String roomId;
    private String type;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
