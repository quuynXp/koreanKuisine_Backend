package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.GroupSessionDTO;
import com.connectJPA.LinguaVietnameseApp.entity.GroupSession;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:41+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class GroupSessionMapperImpl implements GroupSessionMapper {

    @Override
    public GroupSessionDTO toDto(GroupSession entity) {
        if ( entity == null ) {
            return null;
        }

        GroupSessionDTO.GroupSessionDTOBuilder groupSessionDTO = GroupSessionDTO.builder();

        if ( entity.getGroupSessionId() != null ) {
            groupSessionDTO.groupSessionId( entity.getGroupSessionId().toString() );
        }
        if ( entity.getRoomId() != null ) {
            groupSessionDTO.roomId( entity.getRoomId().toString() );
        }
        if ( entity.getUserId() != null ) {
            groupSessionDTO.userId( entity.getUserId().toString() );
        }
        if ( entity.getLessonId() != null ) {
            groupSessionDTO.lessonId( entity.getLessonId().toString() );
        }
        groupSessionDTO.startedAt( entity.getStartedAt() );
        groupSessionDTO.endedAt( entity.getEndedAt() );
        groupSessionDTO.createdAt( entity.getCreatedAt() );
        groupSessionDTO.updatedAt( entity.getUpdatedAt() );
        if ( entity.getIsDeleted() != null ) {
            groupSessionDTO.isDeleted( entity.getIsDeleted() );
        }

        return groupSessionDTO.build();
    }

    @Override
    public GroupSession toEntity(GroupSessionDTO dto) {
        if ( dto == null ) {
            return null;
        }

        GroupSession.GroupSessionBuilder<?, ?> groupSession = GroupSession.builder();

        if ( dto.getGroupSessionId() != null ) {
            groupSession.groupSessionId( UUID.fromString( dto.getGroupSessionId() ) );
        }
        if ( dto.getLessonId() != null ) {
            groupSession.lessonId( UUID.fromString( dto.getLessonId() ) );
        }
        if ( dto.getRoomId() != null ) {
            groupSession.roomId( UUID.fromString( dto.getRoomId() ) );
        }
        if ( dto.getUserId() != null ) {
            groupSession.userId( UUID.fromString( dto.getUserId() ) );
        }
        groupSession.startedAt( dto.getStartedAt() );
        groupSession.endedAt( dto.getEndedAt() );
        groupSession.createdAt( dto.getCreatedAt() );
        groupSession.updatedAt( dto.getUpdatedAt() );

        return groupSession.build();
    }
}
