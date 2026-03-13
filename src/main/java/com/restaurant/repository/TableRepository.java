package com.restaurant.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.restaurant.entity.RestaurantTable;

public interface TableRepository extends JpaRepository<RestaurantTable, Long> {

}