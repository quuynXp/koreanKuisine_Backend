package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonQuestionDTO;
import com.connectJPA.LinguaVietnameseApp.entity.LessonQuestion;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonQuestionMapper {
    LessonQuestionDTO toDto(LessonQuestion entity);
    LessonQuestion toEntity(LessonQuestionDTO dto);
}
