package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.Booking;
import com.connectJPA.demo.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    public Booking updateBooking(String id, Booking updatedBooking) {
        return bookingRepository.findById(id)
                .map(booking -> {
                    booking.setName(updatedBooking.getName());
                    booking.setEmail(updatedBooking.getEmail());
                    booking.setPhone(updatedBooking.getPhone());
                    booking.setNumberOfGuests(updatedBooking.getNumberOfGuests());
                    booking.setBranch(updatedBooking.getBranch());
                    booking.setTable(updatedBooking.getTable());
                    booking.setNote(updatedBooking.getNote());
                    booking.setBookingDate(updatedBooking.getBookingDate());
                    return bookingRepository.save(booking);
                }).orElseThrow(() -> new RuntimeException("Booking not found"));
    }

    public List<Booking> findBookingsByEmailOrPhone(String emailOrPhone) {
        return bookingRepository.findByEmailOrPhone(emailOrPhone, emailOrPhone);
    }

    public void deleteBooking(String id) {
        bookingRepository.deleteById(id);
    }
}
