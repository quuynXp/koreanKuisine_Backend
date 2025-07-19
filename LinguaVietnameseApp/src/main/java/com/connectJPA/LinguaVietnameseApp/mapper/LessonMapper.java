package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Lesson;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonMapper {
    LessonDTO toDto(Lesson lesson);
    Lesson toEntity(LessonDTO dto);
}
