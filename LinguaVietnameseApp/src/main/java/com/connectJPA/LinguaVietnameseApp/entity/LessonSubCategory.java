package com.connectJPA.LinguaVietnameseApp.entity;

import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "lesson_Sub_Category")
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LessonSubCategory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "lesson_sub_category_id")
    private UUID lessonSubCategoryId;

    @Column(name = "lesson_sub_category_name", nullable = false, unique = true)
    private String lessonSubCategoryName;

    @Column(name = "lesson_category_id")
    private UUID lessonCategoryId;

}
