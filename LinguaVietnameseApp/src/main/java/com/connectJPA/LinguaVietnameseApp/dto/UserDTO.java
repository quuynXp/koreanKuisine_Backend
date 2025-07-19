package com.connectJPA.LinguaVietnameseApp.dto;

import com.connectJPA.LinguaVietnameseApp.entity.Character3D;
import com.connectJPA.LinguaVietnameseApp.entity.Language;
import com.connectJPA.LinguaVietnameseApp.entity.Role;
import lombok.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String userId;
    private String email;
    private String fullName;
    private String nickname;
    private String username;
    private String avatarUrl;
    private String phone;
    private String character3dName;
    private String nativeLanguageName;
    private int streak;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDeleted;
}
