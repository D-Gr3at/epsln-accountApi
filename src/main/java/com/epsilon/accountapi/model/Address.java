package com.epsilon.accountapi.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Embeddable
public class Address {
    @Column(length = 11)
    private String houseNumber;
    private String street;
    private String city;
    private String state;
    @Column(length = 80)
    private String country;
    @Column(length = 10)
    private String zip;
}
