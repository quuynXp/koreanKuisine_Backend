package com.connectJPA.LinguaVietnameseApp.dto;


import com.connectJPA.LinguaVietnameseApp.entity.User;
import com.connectJPA.LinguaVietnameseApp.enums.LogAction;
import com.connectJPA.LinguaVietnameseApp.enums.TableLog;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LogDTO {
    private String logId;
    private LogAction action;
    private TableLog targetTable;
    private String targetId;
    private String description;
    private String userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}

