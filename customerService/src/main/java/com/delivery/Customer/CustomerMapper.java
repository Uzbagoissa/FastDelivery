package com.delivery.Customer;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CustomerMapper {

    public static CustomerDtoOut toCustomerDtoOut(Customer customer) {
        return new CustomerDtoOut(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone()
        );
    }

    public static Customer toCustomer(CustomerDtoIn customerDtoIn) {
        Customer customer = new Customer();
        customer.setName(customerDtoIn.getName());
        customer.setEmail(customerDtoIn.getEmail());
        customer.setPhone(customerDtoIn.getPhone());
        return customer;
    }

    public static List<CustomerDtoOut> toListCustomerDtoOut(Iterable<Customer> customers) {
        List<CustomerDtoOut> result = new ArrayList<>();
        for (Customer customer : customers) {
            result.add(toCustomerDtoOut(customer));
        }
        return result;
    }

}