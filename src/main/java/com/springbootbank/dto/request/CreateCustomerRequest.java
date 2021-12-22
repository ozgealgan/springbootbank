package com.springbootbank.dto.request;

import com.springbootbank.dto.enums.CityDto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCustomerRequest extends BaseCustomerRequest{

    private String Id;
}
