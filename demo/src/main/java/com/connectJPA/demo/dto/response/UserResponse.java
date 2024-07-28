package com.connectJPA.demo.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String Id;
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;

}
