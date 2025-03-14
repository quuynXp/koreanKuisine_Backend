package com.connectJPA.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String roomNumber;
    String type; // (Standard, Deluxe, Suite,...)
    int capacity;
    double pricePerNight;
    boolean isAvailable;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    List<RoomBooking> bookings;
}
