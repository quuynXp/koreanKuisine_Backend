package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}


