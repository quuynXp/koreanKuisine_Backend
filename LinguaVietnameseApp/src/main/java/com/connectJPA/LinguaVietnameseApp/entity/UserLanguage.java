package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.UserLanguageId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_languages")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLanguage extends BaseEntity {

    @EmbeddedId
    private UserLanguageId id;
}
