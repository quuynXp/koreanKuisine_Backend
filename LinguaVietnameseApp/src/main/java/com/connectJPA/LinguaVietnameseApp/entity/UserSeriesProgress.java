package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.UserSeriesProgressId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_series_progress")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UserSeriesProgress extends BaseEntity {
    @EmbeddedId
    private UserSeriesProgressId id;

    @Column(name = "current_index", nullable = false)
    private Integer currentIndex;

    @Column(name = "started_at", nullable = false)
    private LocalDateTime startedAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;
}

