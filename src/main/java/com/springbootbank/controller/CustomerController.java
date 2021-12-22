package com.springbootbank.controller;

import com.springbootbank.dto.CustomerDto;
import com.springbootbank.dto.request.CreateCustomerRequest;
import com.springbootbank.dto.request.UpdateCustomerRequest;
import com.springbootbank.service.CustomerSevice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    private final CustomerSevice customerSevice;

    public CustomerController(CustomerSevice customerSevice) {
        this.customerSevice = customerSevice;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CreateCustomerRequest customerRequest){
        return ResponseEntity.ok(customerSevice.createCustomer(customerRequest));
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCustomers(){
        return ResponseEntity.ok(customerSevice.getAllCustomers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable String id){
        return ResponseEntity.ok(customerSevice.getCustomerDtoById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable String id){
        customerSevice.deleteCustomer(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable String id,
                                                      @RequestBody UpdateCustomerRequest customerRequest){
        return ResponseEntity.ok(customerSevice.updateCustomer(id, customerRequest));
    }
 }
