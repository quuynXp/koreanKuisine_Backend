package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.LogDTO;
import com.connectJPA.LinguaVietnameseApp.entity.Log;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LogMapper {
    LogDTO toDto(Log entity);
    Log toEntity(LogDTO dto);
}
