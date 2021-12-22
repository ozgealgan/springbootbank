package com.springbootbank.service;

import com.springbootbank.dto.AccountDto;
import com.springbootbank.dto.converter.AccountDtoConverter;
import com.springbootbank.dto.request.CreateAccountRequest;
import com.springbootbank.dto.request.UpdateAccountRequest;
import com.springbootbank.model.Account;
import com.springbootbank.model.Customer;
import com.springbootbank.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerSevice customerSevice;
    private final AccountDtoConverter accountDtoConverter;

    public AccountService(AccountRepository accountRepository, CustomerSevice customerSevice, AccountDtoConverter accountDtoConverter) {
        this.accountRepository = accountRepository;
        this.customerSevice = customerSevice;
        this.accountDtoConverter = accountDtoConverter;
    }

    public AccountDto createAccount(CreateAccountRequest request){

        Customer customer = customerSevice.getCustomerById(request.getCustomerId());

        if(Objects.isNull(customer.getId()) || customer.getId().trim().equals("")){
            return AccountDto.builder().build();
        }

        Account account = Account.builder()
                .id(request.getId())
                .balance(request.getBalance())
                .currency(request.getCurrency())
                .customerId(request.getCustomerId())
                .city(request.getCity())
                .build();

        return accountDtoConverter.convertFromAccount(accountRepository.save(account));
    }

    public AccountDto updateAccount(String id, UpdateAccountRequest request){

        Customer customer = customerSevice.getCustomerById(request.getCustomerId());

        if(Objects.isNull(customer.getId())){
            return AccountDto.builder().build();
        }

        Optional<Account> accountOptinal = accountRepository.findById(id);
        accountOptinal.ifPresent(account -> {
            account.setBalance(request.getBalance());
            account.setCity(request.getCity());
            account.setCurrency(request.getCurrency());
            account.setCustomerId(request.getCustomerId());
            accountRepository.save(account);
        });

        return accountOptinal.map(accountDtoConverter::convertFromAccount).orElse(new AccountDto());
    }

    public List<AccountDto> getAllAccounts(){

        List<Account> accountList = accountRepository.findAll();

        return accountList.stream().map(accountDtoConverter::convertFromAccount).collect(Collectors.toList());
    }

    public AccountDto getAccountById(String id){
        return accountRepository.findById(id).map(accountDtoConverter::convertFromAccount).orElse(new AccountDto());
    }

    public void deleteAccount(String id){
        accountRepository.deleteById(id);
    }

    public AccountDto withdrawMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);

        accountOptional.ifPresent(account -> {
            if(account.getBalance() > amount){
                account.setBalance(account.getBalance() - amount);
                accountRepository.save(account);
            }else {
                System.out.println("Insufficient funds -> accountId: " + account.getBalance() + " amount: " + amount);
            }
        });

        return  accountOptional.map(accountDtoConverter::convertFromAccount).orElse(new AccountDto());
    }

    public AccountDto addMoney(String id, Double amount){
        Optional<Account> accountOptional = accountRepository.findById(id);

        accountOptional.ifPresent(account -> {
                account.setBalance(account.getBalance() + amount);
                accountRepository.save(account);
        });

        return  accountOptional.map(accountDtoConverter::convertFromAccount).orElse(new AccountDto());
    }

}
