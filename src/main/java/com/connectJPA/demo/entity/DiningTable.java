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
public class DiningTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    String id;

    String tableNumber;
    int capacity;
    boolean isAvailable;

    @OneToMany(mappedBy = "table", cascade = CascadeType.ALL)
    private List<TableBooking> bookings;
}
