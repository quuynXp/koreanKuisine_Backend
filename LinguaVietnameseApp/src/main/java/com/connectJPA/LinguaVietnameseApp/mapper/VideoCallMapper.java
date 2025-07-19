package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallDTO;
import com.connectJPA.LinguaVietnameseApp.entity.VideoCall;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoCallMapper {
    VideoCallDTO toDto(VideoCall entity);
    VideoCall toEntity(VideoCallDTO dto);
}
