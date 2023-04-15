package com.delivery.Order;

import com.delivery.exceptions.IncorrectParameterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDtoOut> getAllOrders(@RequestParam(value = "ids", required = false) List<Long> ids) {
        log.info("Заказы найдены");
        return orderService.getAllOrders(ids);
    }

    @PostMapping("/{customerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDtoOut saveOrder(@Valid @RequestBody OrderDtoIn orderDtoIn,
                                 @PathVariable Long customerId) {
        log.info("Заказ зарегистрирован");
        return orderService.saveOrder(orderDtoIn, customerId);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeOrder(@PathVariable long orderId) {
        log.info("Заказ удален");
        orderService.removeOrder(orderId);
    }
}
