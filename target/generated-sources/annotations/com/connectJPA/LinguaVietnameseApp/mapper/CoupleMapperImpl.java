package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.CoupleDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Couple;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class CoupleMapperImpl implements CoupleMapper {

    @Override
    public CoupleDTO toDto(Couple entity) {
        if ( entity == null ) {
            return null;
        }

        CoupleDTO.CoupleDTOBuilder coupleDTO = CoupleDTO.builder();

        coupleDTO.createdAt( entity.getCreatedAt() );
        coupleDTO.updatedAt( entity.getUpdatedAt() );

        return coupleDTO.build();
    }

    @Override
    public Couple toEntity(CoupleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Couple.CoupleBuilder<?, ?> couple = Couple.builder();

        couple.createdAt( dto.getCreatedAt() );
        couple.updatedAt( dto.getUpdatedAt() );

        return couple.build();
    }
}
