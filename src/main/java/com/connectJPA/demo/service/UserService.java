package com.connectJPA.demo.service;

import com.connectJPA.demo.dto.request.UserCreationRequest;
import com.connectJPA.demo.dto.request.UserUpdateRequest;
import com.connectJPA.demo.dto.response.UserResponse;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.enums.Role;
import com.connectJPA.demo.exception.AppException;
import com.connectJPA.demo.exception.ErrorCode;
import com.connectJPA.demo.mapper.UserMapper;
import com.connectJPA.demo.repository.RoleRepository;
import com.connectJPA.demo.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    public UserResponse createUser(UserCreationRequest request){
        if(userRepository.existsByUsername(request.getUsername())){
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

//        user.setRoles(roles);
        return userMapper.toUserResponse(userRepository.save(user));
    }
    public User findOrCreateUserByEmailAndPhone(String mail, String phone) {
        return userRepository.findByEmailOrPhone(mail, phone)
                .orElseGet(() -> {
                    User newUser = User.builder()
                            .mail(mail)
                            .phone(phone)
                            .build();
                    return userRepository.save(newUser);
                });
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<UserResponse> getUser(){
        log.info("In method get Users");
        return userRepository.findAll().stream()
                .map(userMapper::toUserResponse)
                .collect(Collectors.toList());
    }

    @PostAuthorize("returnObject.username == authentication.name || hasRole('ADMIN')")
    public UserResponse getUser(String Id){
        log.info("In method get user by Id");
        return userMapper.toUserResponse(userRepository.findById(Id)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED)));
    }


    public UserResponse getMyInfo(){
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return userMapper.toUserResponse(user);
    }


    public UserResponse updateUser(String userId, UserUpdateRequest request){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        userMapper.updateUser(user, request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        var roles = roleRepository.findAllById(request.getRoles());
//        user.setRoles(new HashSet<>(roles));

        return userMapper.toUserResponse(userRepository.save(user));
    }

    public void deleteUser(String userId){
        userRepository.deleteById(userId);
    }

}
