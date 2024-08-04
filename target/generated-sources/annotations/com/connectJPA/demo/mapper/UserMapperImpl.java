package com.connectJPA.demo.mapper;

import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-03T16:15:10+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 22.0.1 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserResponse toUserResponse(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.id( user.getId() );
        userResponse.username( user.getUsername() );
        userResponse.firstName( user.getFirstName() );
        userResponse.lastName( user.getLastName() );
        userResponse.dayOfBirth( user.getDayOfBirth() );
        Set<String> set = user.getRoles();
        if ( set != null ) {
            userResponse.roles( new LinkedHashSet<String>( set ) );
        }

        return userResponse.build();
    }

    @Override
    public User toUser(UserCreationRequest request) {
        if ( request == null ) {
            return null;
        }

        User.UserBuilder user = User.builder();

        user.username( request.getUsername() );
        user.password( request.getPassword() );
        user.mail( request.getMail() );
        user.phone( request.getPhone() );
        user.firstName( request.getFirstName() );
        user.lastName( request.getLastName() );
        user.dayOfBirth( request.getDayOfBirth() );
        Set<String> set = request.getRoles();
        if ( set != null ) {
            user.roles( new LinkedHashSet<String>( set ) );
        }

        return user.build();
    }

    @Override
    public void updateUser(User user, UserUpdateRequest request) {
        if ( request == null ) {
            return;
        }

        user.setUsername( request.getUsername() );
        user.setPassword( request.getPassword() );
        user.setMail( request.getMail() );
        user.setPhone( request.getPhone() );
        user.setFirstName( request.getFirstName() );
        user.setLastName( request.getLastName() );
        user.setDayOfBirth( request.getDayOfBirth() );
        if ( user.getRoles() != null ) {
            Set<String> set = request.getRoles();
            if ( set != null ) {
                user.getRoles().clear();
                user.getRoles().addAll( set );
            }
            else {
                user.setRoles( null );
            }
        }
        else {
            Set<String> set = request.getRoles();
            if ( set != null ) {
                user.setRoles( new LinkedHashSet<String>( set ) );
            }
        }
    }
}
