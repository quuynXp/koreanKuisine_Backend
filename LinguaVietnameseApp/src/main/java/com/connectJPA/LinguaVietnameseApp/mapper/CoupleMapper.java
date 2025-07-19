package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.CoupleDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Couple;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CoupleMapper {
    CoupleDTO toDto(Couple entity);
    Couple toEntity(CoupleDTO dto);
}
