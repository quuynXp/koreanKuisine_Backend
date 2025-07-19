package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.GroupAnswerDTO;
import com.connectJPA.LinguaVietnameseApp.entity.GroupAnswer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupAnswerMapper {
    GroupAnswerDTO toDto(GroupAnswer entity);
    GroupAnswer toEntity(GroupAnswerDTO dto);
}
