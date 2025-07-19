package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.BadgeDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Badge;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class BadgeMapperImpl implements BadgeMapper {

    @Override
    public BadgeDTO toDto(Badge entity) {
        if ( entity == null ) {
            return null;
        }

        BadgeDTO.BadgeDTOBuilder badgeDTO = BadgeDTO.builder();

        badgeDTO.badgeName( entity.getBadgeName() );
        badgeDTO.createdAt( entity.getCreatedAt() );
        badgeDTO.updatedAt( entity.getUpdatedAt() );

        return badgeDTO.build();
    }

    @Override
    public Badge toEntity(BadgeDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Badge.BadgeBuilder<?, ?> badge = Badge.builder();

        badge.createdAt( dto.getCreatedAt() );
        badge.updatedAt( dto.getUpdatedAt() );
        badge.badgeName( dto.getBadgeName() );

        return badge.build();
    }
}
