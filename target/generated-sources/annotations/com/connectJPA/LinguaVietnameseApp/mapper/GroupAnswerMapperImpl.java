package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.GroupAnswerDTO;
import com.connectJPA.LinguaVietnameseApp.entity.GroupAnswer;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class GroupAnswerMapperImpl implements GroupAnswerMapper {

    @Override
    public GroupAnswerDTO toDto(GroupAnswer entity) {
        if ( entity == null ) {
            return null;
        }

        GroupAnswerDTO.GroupAnswerDTOBuilder groupAnswerDTO = GroupAnswerDTO.builder();

        if ( entity.getUserId() != null ) {
            groupAnswerDTO.userId( entity.getUserId().toString() );
        }
        groupAnswerDTO.selectedOption( entity.getSelectedOption() );
        if ( entity.getIsCorrect() != null ) {
            groupAnswerDTO.isCorrect( entity.getIsCorrect() );
        }
        groupAnswerDTO.createdAt( entity.getCreatedAt() );
        groupAnswerDTO.updatedAt( entity.getUpdatedAt() );

        return groupAnswerDTO.build();
    }

    @Override
    public GroupAnswer toEntity(GroupAnswerDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GroupAnswer.GroupAnswerBuilder<?, ?> groupAnswer = GroupAnswer.builder();

        groupAnswer.createdAt( dto.getCreatedAt() );
        groupAnswer.updatedAt( dto.getUpdatedAt() );
        if ( dto.getUserId() != null ) {
            groupAnswer.userId( UUID.fromString( dto.getUserId() ) );
        }
        groupAnswer.selectedOption( dto.getSelectedOption() );

        return groupAnswer.build();
    }
}
