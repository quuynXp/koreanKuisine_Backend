package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "group_answers")
@SuperBuilder
public class GroupAnswer extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "group_answer_id")
    private UUID groupAnswerId;

    @Column(name = "group_session_id")
    private UUID groupSessionId;

    @Column(name = "lesson_question_id")
    private UUID lessonQuestionId;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "selected_option")
    private String selectedOption;

    @Column(name = "is_correct", nullable = false)
    private Boolean isCorrect;

    @Column(name = "answered_at", nullable = false)
    private LocalDateTime answeredAt;

}
