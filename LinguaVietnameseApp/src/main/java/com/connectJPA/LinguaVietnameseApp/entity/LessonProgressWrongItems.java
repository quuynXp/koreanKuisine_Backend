package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.id.LessonProgressWrongItemsId;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "lesson_progress_wrong_items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonProgressWrongItems {

    @EmbeddedId
    private LessonProgressWrongItemsId id;

    @Column(name = "wrong_answer")
    private String wrongAnswer;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;
}
