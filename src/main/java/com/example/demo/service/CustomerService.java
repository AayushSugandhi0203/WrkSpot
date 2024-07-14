package com.example.demo.service;

import com.example.demo.pojo.Customer;
import com.example.demo.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Transactional
    public Customer createCustomer(Customer c) {
        try {
            for(int i=0;i<c.getAddress().size();i++) {
                c.addAddress(c.getAddress().get(i));
            }
            Customer customer = customerRepository.save(c);

            return customer;

        } catch (Exception e) {
            System.out.println("Error occured in creating new customer" + e.getMessage());
            throw new RuntimeException("Error occured in creating new customer" + e.getMessage());
        }
    }

    public List<Customer> getCustomerByParameter(String parameter, String value) {
        try {
            if(parameter == null) {
                return customerRepository.findAll();
            } else {
                System.out.println("Parameter and value is" + parameter + " " + value);
                if(parameter.equals("city")) {
                    return customerRepository.findByParameterCity(value);
                }
                if(parameter.equals("state")) {
                    return customerRepository.findByParameterState(value);
                }
            }
            return null;

        } catch (Exception e) {
            System.out.println("Error occured in getting new customer" + e.getMessage());
            throw new RuntimeException("Error occured in getting new customer" + e.getMessage());
        }
    }
}
