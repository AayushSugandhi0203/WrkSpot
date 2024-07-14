package com.example.demo.controller;

import com.example.demo.pojo.Customer;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<Customer>> createCustomer(@Valid @RequestBody(required = true) Customer customer) {
        try {

            Customer Customers = customerService.createCustomer(customer);
            ApiResponse<Customer> apiResponse = new ApiResponse<>("Data Created Successfully", HttpStatus.OK.value(), true, Customers);
            return ResponseEntity.status(200).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<Customer> apiResponse = new ApiResponse<>(e.getMessage(), 500, true, null);
            return ResponseEntity.status(500).body(apiResponse);
        }
    }

    @GetMapping("/get")
    public ResponseEntity<ApiResponse<List<Customer>>> getCustomer(@RequestParam(required = false) String parameter,@RequestParam(required = false) String value) {
        try {

            List<Customer> customer = customerService.getCustomerByParameter(parameter,value);

            ApiResponse<List<Customer>> apiResponse = new ApiResponse<>("Data Fetched Successfully", HttpStatus.OK.value(), true, customer);
            return ResponseEntity.status(200).body(apiResponse);
        } catch (Exception e) {
            ApiResponse<List<Customer>> apiResponse = new ApiResponse<>(e.getMessage(), 500, true, null);
            return ResponseEntity.status(500).body(apiResponse);
        }
    }

    @GetMapping("/getCommon")
    public ResponseEntity<ApiResponse<List<Customer>>> getCommon() {
        try {
            Customer customer1 = new Customer(1, "Aayush", "Sugandhi", "C1", new BigDecimal("1000"), "898951131", new ArrayList<>());
            Customer customer2 = new Customer(2, "Soumil", "Sugandhi", "C2", new BigDecimal("2000"), "1234567890", new ArrayList<>());
            Customer customer3 = new Customer(3, "Keshav", "Vyas", "C3", new BigDecimal("1500"), "0123456789", new ArrayList<>());
            Customer customer4 = new Customer(4, "Shreyas", "Gore", "C4", new BigDecimal("2500"), "9012345678", new ArrayList<>());
            Customer customer5 = new Customer(5, "Akash", "Kumar", "C5", new BigDecimal("3000"), "3344556677", new ArrayList<>());

            List<Customer> list1 = new ArrayList<>();
            list1.add(customer1);
            list1.add(customer2);
            list1.add(customer3);
            list1.add(customer5);

            List<Customer> list2 = new ArrayList<>();
            list2.add(customer3);
            list2.add(customer2);
            list2.add(customer4);
            list2.add(customer5);

            System.out.println("Only Exist in List 1");
            List<Customer> onlyinA = list1.stream().filter(x -> list2.contains(x)==false).collect(Collectors.toList());
            System.out.println("Only Exist in List 1" + onlyinA);

            System.out.println("Only Exist in List 2");
            List<Customer> onlyinB = list2.stream().filter(x -> list1.contains(x)==false).collect(Collectors.toList());
            System.out.println("Only Exist in List 2" + onlyinB);


            System.out.println("Exist in List 1 and 2");
            List<Customer> inBoth = list1.stream().filter(x -> list2.contains(x)).collect(Collectors.toList());
            System.out.println("Exist in List 1 and 2" + inBoth);

            return null;
        }catch (Exception e) {
            ApiResponse<List<Customer>> apiResponse = new ApiResponse<>(e.getMessage(), 500, true, null);
            return ResponseEntity.status(500).body(apiResponse);
        }
    }
}
