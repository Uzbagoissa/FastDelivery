package com.delivery.Order;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class OrderMapper {

    public static OrderDtoOut toOrderDtoOut(Order order, List<Long> foodIds) {
        return new OrderDtoOut(
                order.getId(),
                order.getCustomerId(),
                order.getPersonsNumber(),
                order.getComments(),
                order.getAddress(),
                order.getApartmentOrOffice(),
                order.getPaymentType(),
                order.getOperatorCall(),
                order.getConditionAgreement(),
                foodIds
        );
    }

    public static Order toOrder(OrderDtoIn orderDtoIn, Long customerId) {
        Order order = new Order();
        order.setCustomerId(customerId);
        order.setPersonsNumber(orderDtoIn.getPersonsNumber());
        order.setComments(orderDtoIn.getComments());
        order.setAddress(orderDtoIn.getAddress());
        order.setApartmentOrOffice(orderDtoIn.getApartmentOrOffice());
        order.setPaymentType(orderDtoIn.getPaymentType());
        order.setOperatorCall(orderDtoIn.getOperatorCall());
        order.setConditionAgreement(orderDtoIn.getConditionAgreement());
        return order;
    }

}