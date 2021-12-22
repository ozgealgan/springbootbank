package com.springbootbank.model;

import com.springbootbank.model.enums.City;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

    @Id
    private String id;
    private String name;
    private Integer dateOfbirth;
    private City city;
    private String address;
}
