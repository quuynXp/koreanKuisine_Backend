package com.connectJPA.demo.dto.response;

import com.connectJPA.demo.entity.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;

    String username;
    String email;
    String phone;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;
    Set<Role> roles;
}
