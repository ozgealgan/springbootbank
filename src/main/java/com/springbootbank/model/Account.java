package com.springbootbank.model;

import com.springbootbank.model.enums.City;
import com.springbootbank.model.enums.Currency;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Account {

    @Id
    private String id;

    private String customerId;
    private Double balance;
    private City city;
    private Currency currency;
}
