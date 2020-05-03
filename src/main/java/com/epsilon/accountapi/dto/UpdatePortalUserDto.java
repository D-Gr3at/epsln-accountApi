package com.epsilon.accountapi.dto;

import com.epsilon.accountapi.enumeration.GenderConstant;
import lombok.Data;

@Data
public class UpdatePortalUserDto {

    private String firstName;

    private String lastName;

    private String middleName;

    private String phoneNumber;

    private String password;

    private GenderConstant gender;

    private String houseNumber;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zip;
}
