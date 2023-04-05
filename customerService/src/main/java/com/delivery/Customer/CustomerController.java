package com.delivery.Customer;

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
@RequestMapping(path = "/customers")
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDtoOut> getAllCustomers(@RequestParam(value = "ids", required = false) List<Long> ids,
                                               @RequestParam(value = "from", defaultValue = "0") long from,
                                               @RequestParam(value = "size", defaultValue = "10") long size) {
        if (from < 0) {
            log.info("Неверный параметр from: {}, from должен быть больше или равен 0 ", from);
            throw new IncorrectParameterException("Неверный параметр from: {}, from должен быть больше или равен 0 " + from);
        }
        if (size <= 0) {
            log.info("Неверный параметр size: {}, size должен быть больше или равен 0 ", size);
            throw new IncorrectParameterException("Неверный параметр size: {}, size должен быть больше или равен 0 " + size);
        }
        log.info("Заказчики найдены");
        return customerService.getAllCustomers(ids, from, size);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDtoOut saveCustomer(@Valid @RequestBody CustomerDtoIn customerDtoIn) {
        log.info("Заказчик зарегистрирован");
        return customerService.saveCustomer(customerDtoIn);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCustomer(@PathVariable long customerId) {
        log.info("Заказчик удален");
        customerService.removeCustomer(customerId);
    }

    @PatchMapping("/{customerId}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDtoOut updateCustomer(@PathVariable long customerId,
                                 @RequestBody CustomerDtoInUpdate customerDtoInUpdate) {
        log.info("Данные заказчика обновлены");
        return customerService.updateCustomer(customerId, customerDtoInUpdate);
    }
}
