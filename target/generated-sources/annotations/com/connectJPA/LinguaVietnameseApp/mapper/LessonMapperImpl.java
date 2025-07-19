package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Lesson;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LessonMapperImpl implements LessonMapper {

    @Override
    public LessonDTO toDto(Lesson lesson) {
        if ( lesson == null ) {
            return null;
        }

        LessonDTO.LessonDTOBuilder lessonDTO = LessonDTO.builder();

        lessonDTO.lessonName( lesson.getLessonName() );
        lessonDTO.title( lesson.getTitle() );
        lessonDTO.type( lesson.getType() );
        if ( lesson.getExpReward() != null ) {
            lessonDTO.expReward( lesson.getExpReward() );
        }
        lessonDTO.createdAt( lesson.getCreatedAt() );
        lessonDTO.updatedAt( lesson.getUpdatedAt() );

        return lessonDTO.build();
    }

    @Override
    public Lesson toEntity(LessonDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Lesson.LessonBuilder<?, ?> lesson = Lesson.builder();

        lesson.createdAt( dto.getCreatedAt() );
        lesson.updatedAt( dto.getUpdatedAt() );
        lesson.lessonName( dto.getLessonName() );
        lesson.title( dto.getTitle() );
        lesson.type( dto.getType() );
        lesson.expReward( dto.getExpReward() );

        return lesson.build();
    }
}
