package com.connectJPA.LinguaVietnameseApp.entity.id;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLanguageId implements Serializable {

    @Column(name = "language_id", nullable = false)
    private UUID languageId;

    @Column(name = "user_id", nullable = false)
    private UUID userId;
}
