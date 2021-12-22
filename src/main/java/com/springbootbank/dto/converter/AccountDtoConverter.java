package com.springbootbank.dto.converter;

import com.springbootbank.dto.AccountDto;
import com.springbootbank.dto.enums.CityDto;
import com.springbootbank.model.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDtoConverter {

    public AccountDto convertFromAccount(Account account){

        return AccountDto.builder()
                .balance(account.getBalance())
                .currency(account.getCurrency())
                .id(account.getId())
                .customerId(account.getCustomerId())
                .city(CityDto.valueOf(account.getCity().name()))
                .build();
    }
}
