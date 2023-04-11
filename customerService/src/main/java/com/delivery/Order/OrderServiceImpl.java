package com.delivery.Order;

import com.delivery.exceptions.ConflictException;
import com.delivery.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository repository;

    @Override
    public List<OrderDtoOut> getAllOrders(List<Long> ids) {
        List<OrderDtoOut> orderDtoOuts = new ArrayList<>();
        if (ids.size() == 0) {
            orderDtoOuts = OrderMapper.toListOrderDtoOut(repository.findAll());
        } else {
            for (Long id : ids) {
                Order orderExist = repository.findOrderById(id);
                if (orderExist != null) {
                    orderDtoOuts.add(OrderMapper.toOrderDtoOut(orderExist));
                }
            }

        }
        return orderDtoOuts.stream()
                .sorted(Comparator.comparing(OrderDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderDtoOut saveOrder(OrderDtoIn orderDtoIn) {
        return OrderMapper.toOrderDtoOut(repository.save(OrderMapper.toOrder(orderDtoIn)), orderDtoIn.getFoodIds());
    }

    @Transactional
    @Override
    public void removeOrder(long orderId) {
        orderValid(orderId);
        repository.deleteById(orderId);
    }

    private void orderValid(long orderId) {
        if (repository.findOrderById(orderId) == null) {
            log.error("Заказ c {} не найден или недоступен", orderId);
            throw new NotFoundException("Заказ не найден или недоступен");
        }
    }
}