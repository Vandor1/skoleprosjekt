package com.prosjekt.prosjekt.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT s FROM Order s WHERE s.userId =?1")
    List<Order> findOrdersByUserId(Long userId);
    Optional<Order> findOrderByUserIdAndOrderStatus(Long userId, OrderStatus status);
}
