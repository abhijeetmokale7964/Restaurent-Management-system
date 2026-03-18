package com.restaurant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.restaurant.entity.MenuItem;
import com.restaurant.service.MenuService;

@RestController
@RequestMapping("/api/menu")
public class MenuController {

    @Autowired
    private MenuService service;

 
    // Add menu item
    @PostMapping("/add")
    public MenuItem addMenuItem(@RequestBody MenuItem item) {

        return service.addItem(item);
    }


    // Get all menu items
    @GetMapping("/all")
    public List<MenuItem> getAllMenuItems() {

        return service.getAllItems();
    }


    // Update item
    @PutMapping("/update/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id,
                                   @RequestBody MenuItem item) {

        return service.updateItem(id, item);
    }


    // Delete item
    @DeleteMapping("/delete/{id}")
    public String deleteMenuItem(@PathVariable Long id) {

        service.deleteItem(id);

        return "Item deleted successfully";
    }

}