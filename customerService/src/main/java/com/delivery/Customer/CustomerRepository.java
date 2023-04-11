package com.delivery.Customer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerById(Long id);

    Customer findCustomerByEmail(String email);
}