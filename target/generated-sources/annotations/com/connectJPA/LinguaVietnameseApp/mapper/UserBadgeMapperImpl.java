package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.UserBadgeDTO;
import com.connectJPA.LinguaVietnameseApp.entity.UserBadge;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserBadgeMapperImpl implements UserBadgeMapper {

    @Override
    public UserBadgeDTO toDto(UserBadge entity) {
        if ( entity == null ) {
            return null;
        }

        UserBadgeDTO.UserBadgeDTOBuilder userBadgeDTO = UserBadgeDTO.builder();

        userBadgeDTO.createdAt( entity.getCreatedAt() );
        userBadgeDTO.updatedAt( entity.getUpdatedAt() );

        return userBadgeDTO.build();
    }

    @Override
    public UserBadge toEntity(UserBadgeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserBadge.UserBadgeBuilder<?, ?> userBadge = UserBadge.builder();

        userBadge.createdAt( dto.getCreatedAt() );
        userBadge.updatedAt( dto.getUpdatedAt() );

        return userBadge.build();
    }
}
