package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.Character3DDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Character3D;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:41+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class Character3DMapperImpl implements Character3DMapper {

    @Override
    public Character3DDTO toDto(Character3D character) {
        if ( character == null ) {
            return null;
        }

        Character3DDTO.Character3DDTOBuilder character3DDTO = Character3DDTO.builder();

        character3DDTO.description( character.getDescription() );
        character3DDTO.modelUrl( character.getModelUrl() );
        character3DDTO.createdAt( character.getCreatedAt() );
        character3DDTO.updatedAt( character.getUpdatedAt() );

        return character3DDTO.build();
    }

    @Override
    public Character3D toEntity(Character3DDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Character3D.Character3DBuilder<?, ?> character3D = Character3D.builder();

        character3D.createdAt( dto.getCreatedAt() );
        character3D.updatedAt( dto.getUpdatedAt() );
        character3D.description( dto.getDescription() );
        character3D.modelUrl( dto.getModelUrl() );

        return character3D.build();
    }
}
