package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.LessonQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LessonQuestionRepository extends JpaRepository<LessonQuestion, String> {
    List<LessonQuestion> findByLessonName(String lessonName);
}
