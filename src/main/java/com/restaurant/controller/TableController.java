package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restaurant.entity.RestaurantTable;
import com.restaurant.service.TableService;

@RestController
@RequestMapping("/api/tables")
public class TableController {

    @Autowired
    private TableService service;

    // Add table
    @PostMapping("/add")
    public RestaurantTable addTable(@RequestBody RestaurantTable table) {

        return service.addTable(table);
    }

    // Get all tables
    @GetMapping("/all")
    public List<RestaurantTable> getAllTables() {

        return service.getAllTables();
    }

    // Update table
    @PutMapping("/update/{id}")
    public RestaurantTable updateTable(@PathVariable Long id,
                                       @RequestBody RestaurantTable table) {

        return service.updateTable(id, table);
    }

    // Delete table
    @DeleteMapping("/delete/{id}")
    public String deleteTable(@PathVariable Long id) {

        service.deleteTable(id);

        return "Table deleted successfully";
    }
}