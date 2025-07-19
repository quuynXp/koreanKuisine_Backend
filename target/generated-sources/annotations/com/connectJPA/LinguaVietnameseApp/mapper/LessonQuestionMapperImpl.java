package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LessonQuestionDTO;
import com.connectJPA.LinguaVietnameseApp.entity.LessonQuestion;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class LessonQuestionMapperImpl implements LessonQuestionMapper {

    @Override
    public LessonQuestionDTO toDto(LessonQuestion entity) {
        if ( entity == null ) {
            return null;
        }

        LessonQuestionDTO.LessonQuestionDTOBuilder lessonQuestionDTO = LessonQuestionDTO.builder();

        if ( entity.getLessonQuestionId() != null ) {
            lessonQuestionDTO.lessonQuestionId( entity.getLessonQuestionId().toString() );
        }
        lessonQuestionDTO.question( entity.getQuestion() );
        lessonQuestionDTO.optionA( entity.getOptionA() );
        lessonQuestionDTO.optionB( entity.getOptionB() );
        lessonQuestionDTO.optionC( entity.getOptionC() );
        lessonQuestionDTO.optionD( entity.getOptionD() );
        lessonQuestionDTO.correctOption( entity.getCorrectOption() );
        lessonQuestionDTO.createdAt( entity.getCreatedAt() );
        lessonQuestionDTO.updatedAt( entity.getUpdatedAt() );

        return lessonQuestionDTO.build();
    }

    @Override
    public LessonQuestion toEntity(LessonQuestionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        LessonQuestion.LessonQuestionBuilder<?, ?> lessonQuestion = LessonQuestion.builder();

        lessonQuestion.createdAt( dto.getCreatedAt() );
        lessonQuestion.updatedAt( dto.getUpdatedAt() );
        if ( dto.getLessonQuestionId() != null ) {
            lessonQuestion.lessonQuestionId( UUID.fromString( dto.getLessonQuestionId() ) );
        }
        lessonQuestion.question( dto.getQuestion() );
        lessonQuestion.optionA( dto.getOptionA() );
        lessonQuestion.optionB( dto.getOptionB() );
        lessonQuestion.optionC( dto.getOptionC() );
        lessonQuestion.optionD( dto.getOptionD() );
        lessonQuestion.correctOption( dto.getCorrectOption() );

        return lessonQuestion.build();
    }
}
