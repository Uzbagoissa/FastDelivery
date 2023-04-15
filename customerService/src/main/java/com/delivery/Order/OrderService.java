package com.delivery.Order;

import java.util.List;

public interface OrderService {
    List<OrderDtoOut> getAllOrders(List<Long> ids);

    OrderDtoOut saveOrder(OrderDtoIn orderDtoIn, Long customerId);

    void removeOrder(long orderId);
}