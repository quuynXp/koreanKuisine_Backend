package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonProgressDTO;
import com.connectJPA.LinguaVietnameseApp.entity.LessonProgress;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LessonProgressMapperImpl implements LessonProgressMapper {

    @Override
    public LessonProgressDTO toDto(LessonProgress entity) {
        if ( entity == null ) {
            return null;
        }

        LessonProgressDTO.LessonProgressDTOBuilder lessonProgressDTO = LessonProgressDTO.builder();

        if ( entity.getScore() != null ) {
            lessonProgressDTO.score( entity.getScore() );
        }
        lessonProgressDTO.completedAt( entity.getCompletedAt() );
        lessonProgressDTO.createdAt( entity.getCreatedAt() );
        lessonProgressDTO.updatedAt( entity.getUpdatedAt() );

        return lessonProgressDTO.build();
    }

    @Override
    public LessonProgress toEntity(LessonProgressDTO dto) {
        if ( dto == null ) {
            return null;
        }

        LessonProgress.LessonProgressBuilder<?, ?> lessonProgress = LessonProgress.builder();

        lessonProgress.createdAt( dto.getCreatedAt() );
        lessonProgress.updatedAt( dto.getUpdatedAt() );
        lessonProgress.score( dto.getScore() );
        lessonProgress.completedAt( dto.getCompletedAt() );

        return lessonProgress.build();
    }
}
