package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.UserDTO;
import com.connectJPA.LinguaVietnameseApp.dto.request.UserCreationRequest;
import com.connectJPA.LinguaVietnameseApp.dto.request.UserUpdateRequest;
import com.connectJPA.LinguaVietnameseApp.dto.response.UserResponse;
import com.connectJPA.LinguaVietnameseApp.entity.Language;
import com.connectJPA.LinguaVietnameseApp.entity.Role;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDto(User user);

    User toEntity(UserDTO dto);

    UserResponse toUserResponse(User user);

    User toUser(UserCreationRequest request);

    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}




