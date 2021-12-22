package com.springbootbank.dto.request;

import com.springbootbank.dto.enums.CityDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseCustomerRequest {
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private String address;
}
