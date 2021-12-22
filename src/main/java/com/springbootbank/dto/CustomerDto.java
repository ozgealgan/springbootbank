package com.springbootbank.dto;

import com.springbootbank.dto.enums.CityDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {

    private String Id;
    private String name;
    private Integer dateOfBirth;
    private CityDto city;
    private String address;
}
