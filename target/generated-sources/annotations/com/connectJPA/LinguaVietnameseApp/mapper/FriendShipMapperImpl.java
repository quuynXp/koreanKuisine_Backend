package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.FriendShipDTO;
import com.connectJPA.LinguaVietnameseApp.entity.FriendRequest;
import com.connectJPA.LinguaVietnameseApp.entity.Friendship;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class FriendShipMapperImpl implements FriendShipMapper {

    @Override
    public FriendShipDTO toDto(Friendship entity) {
        if ( entity == null ) {
            return null;
        }

        FriendShipDTO.FriendShipDTOBuilder friendShipDTO = FriendShipDTO.builder();

        if ( entity.getStatus() != null ) {
            friendShipDTO.status( entity.getStatus().name() );
        }
        friendShipDTO.createdAt( entity.getCreatedAt() );
        friendShipDTO.updatedAt( entity.getUpdatedAt() );

        return friendShipDTO.build();
    }

    @Override
    public Friendship toEntity(FriendShipDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Friendship.FriendshipBuilder<?, ?> friendship = Friendship.builder();

        friendship.createdAt( dto.getCreatedAt() );
        friendship.updatedAt( dto.getUpdatedAt() );
        if ( dto.getStatus() != null ) {
            friendship.status( Enum.valueOf( FriendRequest.FriendshipStatus.class, dto.getStatus() ) );
        }

        return friendship.build();
    }
}
