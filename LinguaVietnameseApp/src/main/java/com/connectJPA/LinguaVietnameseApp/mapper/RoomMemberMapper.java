package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.RoomMemberDTO;
import com.connectJPA.LinguaVietnameseApp.entity.RoomMember;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoomMemberMapper {
    RoomMemberDTO toDto(RoomMember entity);
    RoomMember toEntity(RoomMemberDTO dto);
}
