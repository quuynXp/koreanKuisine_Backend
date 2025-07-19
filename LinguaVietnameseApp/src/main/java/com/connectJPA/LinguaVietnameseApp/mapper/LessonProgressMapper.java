package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonProgressDTO;
import com.connectJPA.LinguaVietnameseApp.entity.LessonProgress;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LessonProgressMapper {
    LessonProgressDTO toDto(LessonProgress entity);
    LessonProgress toEntity(LessonProgressDTO dto);
}
