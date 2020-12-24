package com.acc.training.customerservice.service;

import java.util.Optional;

import com.acc.training.customerservice.model.Customer;
import com.acc.training.customerservice.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerService() {
    }

    public Customer getCutomerById(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return customer.get();
        }
        return null;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

}
