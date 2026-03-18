package com.restaurant.service;

import java.util.List;
import com.restaurant.entity.MenuItem;

public interface MenuService {

    MenuItem addItem(MenuItem item);

    List<MenuItem> getAllItems();

    MenuItem updateItem(Long id, MenuItem item);

    void deleteItem(Long id);
}