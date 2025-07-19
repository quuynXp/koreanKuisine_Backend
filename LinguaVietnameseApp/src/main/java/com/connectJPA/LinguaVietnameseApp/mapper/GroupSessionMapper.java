package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.GroupSessionDTO;
import com.connectJPA.LinguaVietnameseApp.entity.GroupSession;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupSessionMapper {
    GroupSessionDTO toDto(GroupSession entity);
    GroupSession toEntity(GroupSessionDTO dto);
}
