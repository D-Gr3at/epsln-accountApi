package com.epsilon.accountapi.dto;

import com.epsilon.accountapi.enumeration.GenderConstant;
import com.epsilon.accountapi.model.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class CreatePortalUserDto {

    @NotBlank
    @Min(value = 2)
    private String firstName;

    @NotBlank
    @Min(value = 2)
    private String lastName;

    private String middleName;

    @NotBlank
    @Email(message = "Email field cannot be empty and must be unique")
    private String email;

    @NotBlank
    @Min(value = 11)
    private String phoneNumber;

    @NotBlank
    private GenderConstant gender;

    @NotBlank
    @Min(value = 6)
    private String password;

    private String houseNumber;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zip;
}
