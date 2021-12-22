package com.springbootbank.dto.converter;

import com.springbootbank.dto.CustomerDto;
import com.springbootbank.dto.enums.CityDto;
import com.springbootbank.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoConverter {

    public CustomerDto convertFromCustomer(Customer customer){

        return CustomerDto.builder()
                .address(customer.getAddress())
                .Id(customer.getId())
                .name(customer.getName())
                .dateOfBirth(customer.getDateOfbirth())
                .city(CityDto.valueOf(customer.getCity().name()))
                .build();
    }
}
