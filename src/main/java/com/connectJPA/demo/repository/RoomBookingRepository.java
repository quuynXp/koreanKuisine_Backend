package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.Room;
import com.connectJPA.demo.entity.RoomBooking;
import com.connectJPA.demo.entity.TableBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomBookingRepository  extends JpaRepository<RoomBooking, String> {
    List<RoomBooking> findByUserId(String userId);
}
