package com.connectJPA.demo.repository;

import com.connectJPA.demo.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, String> {
    List<DiningTable> findByIsAvailableTrue();
}
