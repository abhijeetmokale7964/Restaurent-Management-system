package com.restaurant.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.entity.RestaurantTable;
import com.restaurant.repository.TableRepository;
import com.restaurant.service.TableService;

@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableRepository repository;

    // Add new table
    @Override
    public RestaurantTable addTable(RestaurantTable table) {

        return repository.save(table);
    }

    // Get all tables
    @Override
    public List<RestaurantTable> getAllTables() {

        return repository.findAll();
    }

    // Update table
    @Override
    public RestaurantTable updateTable(Long id, RestaurantTable table) {

        RestaurantTable existing = repository.findById(id).orElseThrow();

        existing.setTableNumber(table.getTableNumber());
        existing.setCapacity(table.getCapacity());
        existing.setStatus(table.getStatus());

        return repository.save(existing);
    }

    // Delete table
    @Override
    public void deleteTable(Long id) {

        repository.deleteById(id);
    }
}