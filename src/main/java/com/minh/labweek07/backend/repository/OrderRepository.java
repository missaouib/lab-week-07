package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}