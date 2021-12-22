package com.springbootbank.service;

import com.springbootbank.dto.CustomerDto;
import com.springbootbank.dto.converter.CustomerDtoConverter;
import com.springbootbank.dto.request.CreateCustomerRequest;
import com.springbootbank.dto.request.UpdateCustomerRequest;
import com.springbootbank.model.Customer;
import com.springbootbank.model.enums.City;
import com.springbootbank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerSevice {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    public CustomerSevice(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public CustomerDto createCustomer(CreateCustomerRequest customerRequest) {
        Customer customer = Customer.builder()
                .id(customerRequest.getId())
                .address(customerRequest.getAddress())
                .name(customerRequest.getName())
                .dateOfbirth(customerRequest.getDateOfBirth())
                .city(City.valueOf(customerRequest.getCity().name()))
                .build();

        Customer savedCustomer = customerRepository.save(customer);



        return customerDtoConverter.convertFromCustomer(savedCustomer);
    }

    public List<CustomerDto> getAllCustomers() {

        List<Customer> customerList = customerRepository.findAll();
        List<CustomerDto> customerDtoList = new ArrayList<>();

        for(Customer customer: customerList){
            customerDtoList.add(customerDtoConverter.convertFromCustomer(customer));
        }

        return customerDtoList;
    }

    public CustomerDto getCustomerDtoById(String id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        return customerOptional.map(customerDtoConverter::convertFromCustomer).orElse(new CustomerDto());
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }

    public CustomerDto updateCustomer(String id, UpdateCustomerRequest customerRequest) {
        Optional<Customer> customerOptional = customerRepository.findById(id);

        customerOptional.ifPresent(customer -> {
            customer.setAddress(customerRequest.getAddress());
            customer.setCity(City.valueOf(customerRequest.getCity().name()));
            customer.setDateOfbirth(customerRequest.getDateOfBirth());
            customer.setName(customerRequest.getName());
            customerRepository.save(customer);
        });

        return customerOptional.map(customerDtoConverter::convertFromCustomer).orElse(new CustomerDto());
    }

    protected Customer getCustomerById(String id){
        return customerRepository.findById(id).orElse(new Customer());
    }
}