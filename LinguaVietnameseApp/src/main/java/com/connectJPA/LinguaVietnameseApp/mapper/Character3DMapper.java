package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.Character3DDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Character3D;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface Character3DMapper {
    Character3DDTO toDto(Character3D character);
    Character3D toEntity(Character3DDTO dto);
}
