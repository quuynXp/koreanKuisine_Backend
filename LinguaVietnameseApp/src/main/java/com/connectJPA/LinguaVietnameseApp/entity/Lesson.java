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
@Table(name = "lessons")
@SuperBuilder
public class Lesson extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "lesson_id")
    private UUID lessonId;

    @Column(name = "lesson_name", nullable = false, unique = true)
    private String lessonName;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "type")
    private String type;

    @Column(name = "exp_reward", nullable = false)
    private Integer expReward;

    @Column(name = "lesson_series_id")
    private UUID lessonSeriesId;

    @Column(name = "lesson_category_id")
    private UUID lessonCategoryId;

    @Column(name = "lesson_sub_category_id")
    private UUID lessonSubCategoryId;
}
