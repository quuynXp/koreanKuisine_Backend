package com.connectJPA.LinguaVietnameseApp.mapper;

import com.connectJPA.LinguaVietnameseApp.dto.UserDTO;
import com.connectJPA.LinguaVietnameseApp.dto.request.UserCreationRequest;
import com.connectJPA.LinguaVietnameseApp.dto.request.UserUpdateRequest;
import com.connectJPA.LinguaVietnameseApp.dto.response.UserResponse;
import com.connectJPA.LinguaVietnameseApp.entity.User;
import java.util.UUID;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-07-13T21:50:42+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO toDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        if ( user.getUserId() != null ) {
            userDTO.userId( user.getUserId().toString() );
        }
        userDTO.email( user.getEmail() );
        userDTO.nickname( user.getNickname() );
        userDTO.username( user.getUsername() );
        userDTO.avatarUrl( user.getAvatarUrl() );
        userDTO.phone( user.getPhone() );
        if ( user.getStreak() != null ) {
            userDTO.streak( user.getStreak() );
        }
        userDTO.createdAt( user.getCreatedAt() );
        userDTO.updatedAt( user.getUpdatedAt() );

        return userDTO.build();
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.createdAt( dto.getCreatedAt() );
        user.updatedAt( dto.getUpdatedAt() );
        if ( dto.getUserId() != null ) {
            user.userId( UUID.fromString( dto.getUserId() ) );
        }
        user.username( dto.getUsername() );
        user.email( dto.getEmail() );
        user.nickname( dto.getNickname() );
        user.phone( dto.getPhone() );
        user.avatarUrl( dto.getAvatarUrl() );
        user.streak( dto.getStreak() );

        return user.build();
    }

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.username( user.getUsername() );
        userResponse.phone( user.getPhone() );

        return userResponse.build();
    }

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder<?, ?> user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.phone( request.getPhone() );

        return user.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setPhone( request.getPhone() );
    }
}
