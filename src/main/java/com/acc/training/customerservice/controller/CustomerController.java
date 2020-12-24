package com.acc.training.customerservice.controller;

import com.acc.training.customerservice.model.Customer;
import com.acc.training.customerservice.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    public CustomerController() {
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Customer getCustomer(@PathVariable("id") String id) {
        // return new Customer("1001", "Laxminarayan", " Lutherville Timonium");
        return customerService.getCutomerById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

}
