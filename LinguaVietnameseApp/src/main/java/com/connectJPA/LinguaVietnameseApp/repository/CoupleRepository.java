package com.connectJPA.LinguaVietnameseApp.repository;

import com.connectJPA.LinguaVietnameseApp.entity.Couple;
import com.connectJPA.LinguaVietnameseApp.entity.id.CouplesId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoupleRepository extends JpaRepository<Couple, CouplesId> {
    void deleteByCoupleIdUser1IdAndCoupleIdUser2Id(String user1Id, String user2Id);
    Optional<Couple> findByCoupleIdUser1IdOrCoupleIdUser2Id(String user1Id, String user2Id);

}

