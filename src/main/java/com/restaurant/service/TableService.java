package com.restaurant.service;

import java.util.List;
import com.restaurant.entity.RestaurantTable;

public interface TableService {

    RestaurantTable addTable(RestaurantTable table);

    List<RestaurantTable> getAllTables();

    RestaurantTable updateTable(Long id, RestaurantTable table);

    void deleteTable(Long id);

}