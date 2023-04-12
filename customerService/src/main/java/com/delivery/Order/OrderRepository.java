package com.delivery.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderById(Long id);
    @Query(value = "SELECT o.food_id " +
            "FROM order_food AS o " +
            "WHERE o.order_id = ?1 ", nativeQuery = true)
    List<Long> findAllFoodIdsByOrderId(long orderId);
}