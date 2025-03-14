package com.connectJPA.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class TableBooking {
    @Id
    @GeneratedValue( strategy = GenerationType.UUID)
    String id;

    String name;
    String email;
    String phone;
    String numberOfGuests;
    String branch;

    @Column(name = "`table`")
    String table;

    String note;


    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    LocalDateTime bookingDate;

}
