package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByEmailOrPhone(String email, String phone);
}
