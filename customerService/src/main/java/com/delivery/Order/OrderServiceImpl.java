package com.delivery.Order;

import com.delivery.exceptions.ConflictException;
import com.delivery.exceptions.IncorrectParameterException;
import com.delivery.exceptions.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
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
    private final JdbcTemplate jdbcTemplate;

    @Override
    public List<OrderDtoOut> getAllOrders(List<Long> ids) {
        List<OrderDtoOut> orderDtoOuts = new ArrayList<>();
        if (ids.size() == 0) {
            for (Order order : repository.findAll()) {
                orderDtoOuts.add(OrderMapper.toOrderDtoOut(order, repository.findAllFoodIdsByOrderId(order.getId())));
            }
        } else {
            for (Long id : ids) {
                Order orderExist = repository.findOrderById(id);
                if (orderExist != null) {
                    orderDtoOuts.add(OrderMapper.toOrderDtoOut(orderExist, repository.findAllFoodIdsByOrderId(id)));
                }
            }

        }
        return orderDtoOuts.stream()
                .sorted(Comparator.comparing(OrderDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public OrderDtoOut saveOrder(OrderDtoIn orderDtoIn, Long customerId) {
        if (orderDtoIn.getPersonsNumber() == null){
            orderDtoIn.setPersonsNumber(1L);
        }
        if (orderDtoIn.getPaymentType() == null || orderDtoIn.getPaymentType().isBlank()){
            orderDtoIn.setPaymentType("PAYMENT_BY_RECEIPT");
        }
        if (orderDtoIn.getOperatorCall() == null){
            orderDtoIn.setOperatorCall(false);
        }
        if (orderDtoIn.getConditionAgreement() == null || orderDtoIn.getConditionAgreement() == false) {
            log.error("Нужно подтвердить согласие с условиями доставки");
            throw new IncorrectParameterException("Нужно подтвердить согласие с условиями доставки");
        }
        if (orderDtoIn.getFoodIds() == null || orderDtoIn.getFoodIds().size() == 0) {
            log.error("Отсутствует список продуктов для заказа");
            throw new NotFoundException("Отсутствует список продуктов для заказа");
        }
        Order order = repository.save(OrderMapper.toOrder(orderDtoIn, customerId));
        for (Long foodId : orderDtoIn.getFoodIds()) {
            String sql = "INSERT INTO order_food(order_id, food_id) VALUES (?, ?) ";
            jdbcTemplate.update(sql, order.getId(), foodId);
        }
        return OrderMapper.toOrderDtoOut(order, orderDtoIn.getFoodIds());
    }

    @Transactional
    @Override
    public void removeOrder(long orderId) {
        orderValid(orderId);
        String sqlDelete = "DELETE FROM order_food WHERE order_id = ? ";
        jdbcTemplate.update(sqlDelete, orderId);
        repository.deleteById(orderId);
    }

    private void orderValid(long orderId) {
        if (repository.findOrderById(orderId) == null) {
            log.error("Заказ c {} не найден или недоступен", orderId);
            throw new NotFoundException("Заказ не найден или недоступен");
        }
    }
}