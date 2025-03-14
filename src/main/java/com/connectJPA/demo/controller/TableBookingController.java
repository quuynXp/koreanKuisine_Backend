package com.connectJPA.demo.controller;

import com.connectJPA.demo.entity.TableBooking;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.service.TableBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/table")
@RequiredArgsConstructor
public class TableBookingController {
    private final TableBookingService tableBookingService;

    @PostMapping("/book")
    public TableBooking bookTable(@RequestParam String tableId,
                                  @RequestParam String email,
                                  @RequestParam String phoneNumber,
                                  @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime bookingTime,
                                  @RequestParam int numberOfPeople) {
        return tableBookingService.bookTable(tableId, email, phoneNumber, bookingTime, numberOfPeople);
    }
}

