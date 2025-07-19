package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.BadgeDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {
    BadgeDTO toDto(Badge entity);
    Badge toEntity(BadgeDTO dto);
}
