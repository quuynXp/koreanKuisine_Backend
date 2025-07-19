package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import com.connectJPA.LinguaVietnameseApp.entity.id.CourseLessonId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@Table(name = "course_lessons")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CourseLesson extends BaseEntity {
    @EmbeddedId
    private CourseLessonId id;

    @Column(name = "order_index", nullable = false)
    private Integer orderIndex = 0;
}

