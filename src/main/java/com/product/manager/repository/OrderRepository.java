package com.product.manager.repository;

import com.product.manager.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByCreationDateBetween(LocalDateTime startTime, LocalDateTime endTime);
}
