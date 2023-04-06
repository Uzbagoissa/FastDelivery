package com.delivery.Customer;

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
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Override
    public List<CustomerDtoOut> getAllCustomers(List<Long> ids, long from, long size) {
        List<CustomerDtoOut> customerDtoOuts = new ArrayList<>();
        if (ids.size() == 0) {
            customerDtoOuts = CustomerMapper.toListCustomerDtoOut(repository.findAll());
        } else {
            for (Long id : ids) {
                Customer customerExist = repository.findCustomerById(id);
                if (customerExist != null) {
                    customerDtoOuts.add(CustomerMapper.toCustomerDtoOut(customerExist));
                }
            }

        }
        return customerDtoOuts.stream()
                .skip(from)
                .limit(size)
                .sorted(Comparator.comparing(CustomerDtoOut::getId))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public CustomerDtoOut saveCustomer(CustomerDtoIn customerDtoIn) {
        emailValid(customerDtoIn.getEmail());
        return CustomerMapper.toCustomerDtoOut(repository.save(CustomerMapper.toCustomer(customerDtoIn)));
    }

    @Transactional
    @Override
    public void removeCustomer(long customerId) {
        userValid(customerId);
        repository.deleteById(customerId);
    }

    @Transactional
    @Override
    public CustomerDtoOut updateCustomer(long customerId, CustomerDtoInUpdate customerDtoInUpdate) {
        userValid(customerId);
        Customer customer = repository.getById(customerId);
        customer.setName(customerDtoInUpdate.getName() == null ? customer.getName() : customerDtoInUpdate.getName());
        if (customerDtoInUpdate.getEmail() == null){
            customer.setEmail(customer.getEmail());
        } else {
            emailValid(customerDtoInUpdate.getEmail());
            customer.setEmail(customerDtoInUpdate.getEmail());
        }
        customer.setPhone(customerDtoInUpdate.getPhone() == null ? customer.getPhone() : customerDtoInUpdate.getPhone());
        customer.setId(customerId);
        return CustomerMapper.toCustomerDtoOut(repository.save(customer));
    }

    private void emailValid(String email) {
        if (repository.findCustomerByEmail(email) != null) {
            log.error("Заказчик c {} уже существует", email);
            throw new ConflictException("Заказчик уже существует");
        }
    }

    private void userValid(long customerId) {
        if (repository.findCustomerById(customerId) == null) {
            log.error("Заказчик c {} не найден или недоступен", customerId);
            throw new NotFoundException("Заказчик не найден или недоступен");
        }
    }
}