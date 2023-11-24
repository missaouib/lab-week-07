package com.minh.labweek07.backend.repository;

import com.minh.labweek07.backend.models.Order;
import com.minh.labweek07.backend.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Order> {
}