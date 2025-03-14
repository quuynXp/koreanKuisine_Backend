package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableBookingRepository extends JpaRepository<TableBooking, String> {
    List<TableBooking> findByUserId(String userId);
}
