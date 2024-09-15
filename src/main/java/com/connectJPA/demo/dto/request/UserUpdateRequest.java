package com.connectJPA.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping
public class UserUpdateRequest {
    String username;
    String password;
    String mail;
    String phone;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;
    Set<String> roles;
}
