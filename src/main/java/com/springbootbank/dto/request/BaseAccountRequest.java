package com.springbootbank.dto.request;

import com.springbootbank.model.enums.City;
import com.springbootbank.model.enums.Currency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseAccountRequest {
    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
