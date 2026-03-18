package com.restaurant.repository;

import com.restaurant.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillRepository extends JpaRepository<Bill, Long> {

    Bill findByOrderId(Long orderId);
}