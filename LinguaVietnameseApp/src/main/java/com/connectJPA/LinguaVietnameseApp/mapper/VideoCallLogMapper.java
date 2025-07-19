package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.VideoCallLogDTO;
import com.connectJPA.LinguaVietnameseApp.entity.VideoCallLog;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VideoCallLogMapper {
    VideoCallLogDTO toDto(VideoCallLog entity);
    VideoCallLog toEntity(VideoCallLogDTO dto);
}
