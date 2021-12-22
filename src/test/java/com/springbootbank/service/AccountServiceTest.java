package com.springbootbank.service;

import com.springbootbank.dto.AccountDto;
import com.springbootbank.dto.converter.AccountDtoConverter;
import com.springbootbank.dto.enums.CityDto;
import com.springbootbank.dto.request.CreateAccountRequest;
import com.springbootbank.model.Account;
import com.springbootbank.model.Customer;
import com.springbootbank.model.enums.City;
import com.springbootbank.model.enums.Currency;
import com.springbootbank.repository.AccountRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class AccountServiceTest {

    private AccountService accountService;

    private AccountRepository accountRepository;
    private CustomerSevice customerSevice;
    private AccountDtoConverter accountDtoConverter;

    @Before
    public void setUp() throws Exception {
        accountRepository = Mockito.mock(AccountRepository.class);
        customerSevice = Mockito.mock(CustomerSevice.class);
        accountDtoConverter = Mockito.mock(AccountDtoConverter.class);

        accountService = new AccountService(accountRepository, customerSevice, accountDtoConverter);
    }

    @Test
    public void whenCreateAccountCalledWithValidRequest_itShouldReturnValidAccountDto() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Customer customer = Customer.builder()
                .id("12345")
                .city(City.ISTANBUL)
                .address("address")
                .dateOfbirth(1996)
                .name("ozge")
                .build();

        Account account = Account.builder()
                .id(createAccountRequest.getId())
                .balance(createAccountRequest.getBalance())
                .currency(createAccountRequest.getCurrency())
                .customerId(createAccountRequest.getCustomerId())
                .city(createAccountRequest.getCity())
                .build();

        AccountDto accountDto = AccountDto.builder()
                .id("1234")
                .customerId("12345")
                .currency(Currency.TRY)
                .balance(100.0)
                .city(CityDto.valueOf(City.ISTANBUL.name()))
                .build();

        Mockito.when(customerSevice.getCustomerById("12345")).thenReturn(customer);
        Mockito.when(accountRepository.save(account)).thenReturn(account);
        Mockito.when(accountDtoConverter.convertFromAccount(account)).thenReturn(accountDto);

        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(result, accountDto);
        Mockito.verify(customerSevice).getCustomerById("12345");
        Mockito.verify(accountRepository).save(account);
        Mockito.verify(accountDtoConverter).convertFromAccount(account);
    }

    @Test
    public void whenCreateAccountCalledWithNonExistCustomer_itShouldReturnEmptyAccountDto() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Mockito.when(customerSevice.getCustomerById("12345")).thenReturn(Customer.builder().build());

        AccountDto expectedAccountDto = AccountDto.builder().build();
        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(expectedAccountDto, result);

        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);
    }

    @Test
    public void whenCreateAccountCalledWithCustomerWithOutId_itShouldReturnEmptyAccountDto() {
        CreateAccountRequest createAccountRequest = new CreateAccountRequest("1234");
        createAccountRequest.setCustomerId("12345");
        createAccountRequest.setBalance(100.0);
        createAccountRequest.setCity(City.ISTANBUL);
        createAccountRequest.setCurrency(Currency.TRY);

        Mockito.when(customerSevice.getCustomerById("12345")).thenReturn(Customer.builder()
                .id(" ")
                .build());

        AccountDto expectedAccountDto = AccountDto.builder().build();
        AccountDto result = accountService.createAccount(createAccountRequest);

        Assert.assertEquals(expectedAccountDto, result);

        Mockito.verifyNoInteractions(accountRepository);
        Mockito.verifyNoInteractions(accountDtoConverter);
    }
}