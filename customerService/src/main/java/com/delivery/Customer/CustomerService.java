package com.delivery.Customer;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerService {
    List<CustomerDtoOut> getAllCustomers(List<Long> ids, long from, long size);

    CustomerDtoOut saveCustomer(CustomerDtoIn customerDtoIn);

    void removeCustomer(long customerId);

    CustomerDtoOut updateCustomer(long customerId, CustomerDtoInUpdate customerDtoInUpdate);
}