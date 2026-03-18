package com.restaurant.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.entity.MenuItem;
import com.restaurant.repository.MenuRepository;
import com.restaurant.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository repo;

    // Add menu item
    @Override
    public MenuItem addItem(MenuItem item) {
        return repo.save(item);
    }

    // Get all menu items
    @Override
    public List<MenuItem> getAllItems() {
        return repo.findAll();
    }

    // Update menu item
    @Override
    public MenuItem updateItem(Long id, MenuItem item) {

        MenuItem existing = repo.findById(id).orElseThrow();

        existing.setName(item.getName());
        existing.setCategory(item.getCategory());
        existing.setPrice(item.getPrice());
        existing.setAvailable(item.getAvailable());

        return repo.save(existing);
    }

    // Delete menu item
    @Override
    public void deleteItem(Long id) {
        repo.deleteById(id);
    }
}