package com.connectJPA.LinguaVietnameseApp.dto;

import com.connectJPA.LinguaVietnameseApp.entity.Room;
import com.connectJPA.LinguaVietnameseApp.enums.RoomStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomDTO {
    private String roomId;
    private String roomName;
    private String creatorId;
    private RoomStatus status;
    private int maxMembers;
    private LocalDateTime createdAt;
    private LocalDateTime deletedAt;
    private boolean isDeleted;

    private Set<String> memberIds;



}
