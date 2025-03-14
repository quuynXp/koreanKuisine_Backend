package com.connectJPA.demo.service;

import com.connectJPA.demo.entity.DiningTable;
import com.connectJPA.demo.repository.DiningTableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiningTableService {
    private final DiningTableRepository diningTableRepository;

    public List<DiningTable> getAllTables() {
        return diningTableRepository.findAll();
    }

    public DiningTable addTable(DiningTable table) {
        return diningTableRepository.save(table);
    }
}

