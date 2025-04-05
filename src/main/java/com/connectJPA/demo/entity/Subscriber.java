package com.connectJPA.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Subscriber {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String email;
}

