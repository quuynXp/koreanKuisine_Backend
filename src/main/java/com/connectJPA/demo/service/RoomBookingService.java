package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.Room;
import com.connectJPA.demo.entity.RoomBooking;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.repository.RoomBookingRepository;
import com.connectJPA.demo.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class RoomBookingService {
    private final RoomBookingRepository roomBookingRepository;
    private final RoomRepository roomRepository;
    private final UserService userService;

    public RoomBooking bookRoom(String roomId, String email, String phone, LocalDate checkIn, LocalDate checkOut) {
        User user = userService.findOrCreateUserByEmailAndPhone(email, phone);

        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));

        long days = ChronoUnit.DAYS.between(checkIn, checkOut);
        double totalPrice = days * room.getPricePerNight();

        RoomBooking booking = RoomBooking.builder()
                .room(room)
                .user(user)
                .checkInDate(checkIn)
                .checkOutDate(checkOut)
                .totalPrice(totalPrice)
                .build();

        room.setAvailable(false);
        roomRepository.save(room);

        return roomBookingRepository.save(booking);
    }
}

