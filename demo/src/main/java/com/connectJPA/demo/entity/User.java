package com.connectJPA.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class User {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    String id;

    String username;
    String password;
    String mail;
    String phone;
    String firstName;
    String lastName;
    LocalDate dayOfBirth;
    Set<String> roles;

}
