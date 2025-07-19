package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.RoomDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Room;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public RoomDTO toDto(Room room) {
        if ( room == null ) {
            return null;
        }

        RoomDTO.RoomDTOBuilder roomDTO = RoomDTO.builder();

        if ( room.getRoomId() != null ) {
            roomDTO.roomId( room.getRoomId().toString() );
        }
        roomDTO.roomName( room.getRoomName() );
        if ( room.getCreatorId() != null ) {
            roomDTO.creatorId( room.getCreatorId().toString() );
        }
        roomDTO.status( room.getStatus() );
        if ( room.getMaxMembers() != null ) {
            roomDTO.maxMembers( room.getMaxMembers() );
        }
        roomDTO.createdAt( room.getCreatedAt() );
        roomDTO.deletedAt( room.getDeletedAt() );

        return roomDTO.build();
    }

    @Override
    public Room toEntity(RoomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Room.RoomBuilder<?, ?> room = Room.builder();

        room.createdAt( dto.getCreatedAt() );
        room.deletedAt( dto.getDeletedAt() );
        if ( dto.getRoomId() != null ) {
            room.roomId( UUID.fromString( dto.getRoomId() ) );
        }
        room.roomName( dto.getRoomName() );
        if ( dto.getCreatorId() != null ) {
            room.creatorId( UUID.fromString( dto.getCreatorId() ) );
        }
        room.maxMembers( dto.getMaxMembers() );
        room.status( dto.getStatus() );

        return room.build();
    }
}
