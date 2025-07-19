package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.RoomMemberDTO;
import com.connectJPA.LinguaVietnameseApp.entity.RoomMember;
import com.connectJPA.LinguaVietnameseApp.entity.base.BaseEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:41+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class RoomMemberMapperImpl implements RoomMemberMapper {

    @Override
    public RoomMemberDTO toDto(RoomMember entity) {
        if ( entity == null ) {
            return null;
        }

        RoomMemberDTO.RoomMemberDTOBuilder roomMemberDTO = RoomMemberDTO.builder();

        if ( entity.getRole() != null ) {
            roomMemberDTO.role( String.valueOf( entity.getRole() ) );
        }
        roomMemberDTO.joinedAt( entity.getJoinedAt() );
        roomMemberDTO.createdAt( entity.getCreatedAt() );
        roomMemberDTO.updatedAt( entity.getUpdatedAt() );

        return roomMemberDTO.build();
    }

    @Override
    public RoomMember toEntity(RoomMemberDTO dto) {
        if ( dto == null ) {
            return null;
        }

        BaseEntity.BaseEntityBuilder<?, ?> roomMember = BaseEntity.builder();

        roomMember.createdAt( dto.getCreatedAt() );
        roomMember.updatedAt( dto.getUpdatedAt() );

        return roomMember.build();
    }
}
