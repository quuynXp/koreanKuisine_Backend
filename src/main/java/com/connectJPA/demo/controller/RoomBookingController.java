package com.connectJPA.demo.controller;

import com.connectJPA.demo.entity.RoomBooking;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.service.RoomBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/room/book")
@RequiredArgsConstructor
public class RoomBookingController {
    private final RoomBookingService roomBookingService;

    public RoomBooking bookRoom(@RequestParam String roomId,
                                @RequestParam String email,
                                @RequestParam String phoneNumber,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkIn,
                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOut) {
        return roomBookingService.bookRoom(roomId, email, phoneNumber, checkIn, checkOut);
    }
}
