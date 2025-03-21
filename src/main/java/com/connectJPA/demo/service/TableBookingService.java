package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.DiningTable;
import com.connectJPA.demo.entity.TableBooking;
import com.connectJPA.demo.entity.User;
import com.connectJPA.demo.repository.DiningTableRepository;
import com.connectJPA.demo.repository.TableBookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TableBookingService {
    private final TableBookingRepository tableBookingRepository;
    private final DiningTableRepository diningTableRepository;
    private final UserService userService;

    public TableBooking bookTable(String tableId, String email, String phoneNumber, LocalDateTime bookingDate, int numberOfPeople) {
        User user = userService.findOrCreateUserByEmailAndPhone(email, phoneNumber);

        DiningTable table = diningTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        TableBooking booking = TableBooking.builder()
                .table(table)
                .user(user)
                .bookingDate(bookingDate)
                .numberOfPeople(numberOfPeople)
                .build();

        table.setAvailable(false);
        diningTableRepository.save(table);

        return tableBookingRepository.save(booking);
    }

    public TableBooking updateBookTable(String tableId, String email, String phoneNumber, LocalDateTime bookingDate, int numberOfPeople) {
        User user = userService.findOrCreateUserByEmailAndPhone(email, phoneNumber);

        DiningTable table = diningTableRepository.findById(tableId)
                .orElseThrow(() -> new RuntimeException("Table not found"));

        TableBooking booking = TableBooking.builder()
                .table(table)
                .user(user)
                .bookingDate(bookingDate)
                .numberOfPeople(numberOfPeople)
                .build();

        table.setAvailable(false);
        diningTableRepository.save(table);

        return tableBookingRepository.save(booking);
    }
}

