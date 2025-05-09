package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(User user);

    User toUser(UserCreationRequest request);


    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}



