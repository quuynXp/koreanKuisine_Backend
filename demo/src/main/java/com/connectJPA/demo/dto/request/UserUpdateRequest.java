package com.connectJPA.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping
public class UserUpdateRequest {
    String username;
    String password;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;

}
