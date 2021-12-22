package com.springbootbank.dto;

import com.springbootbank.dto.enums.CityDto;
import com.springbootbank.model.enums.Currency;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class AccountDto {
    private String id;
    private String customerId;
    private Double balance;
    private CityDto city;
    private Currency currency;
}
