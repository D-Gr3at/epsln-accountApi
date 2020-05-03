package com.epsilon.accountapi.enumeration;

public enum GenderConstant {
    MALE("MALE"),
    FEMALE("FEMALE"),
    ;

    private String gender;

    public String getGender() {
        return gender;
    }

    GenderConstant(String value){
        this.gender = value;
    }
}
