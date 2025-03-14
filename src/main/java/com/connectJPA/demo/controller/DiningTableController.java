package com.connectJPA.demo.controller;

import com.connectJPA.demo.entity.DiningTable;
import com.connectJPA.demo.service.DiningTableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
@RequiredArgsConstructor
public class DiningTableController {
    private final DiningTableService diningTableService;

    @GetMapping
    public List<DiningTable> getAllTables() {
        return diningTableService.getAllTables();
    }

    @PostMapping
    public DiningTable addTable(@RequestBody DiningTable table) {
        return diningTableService.addTable(table);
    }
}

