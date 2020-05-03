package com.epsilon.accountapi.enumeration;

public enum GenericStatusConstant {
    ACTIVE("ACTIVE"),
    DEACTIVATED("DEACTIVATED"),
    INACTIVE("INACTIVE"),
    REVOKED("REVOKED");

    private String status;

    public String getStatus() {
        return status;
    }

    GenericStatusConstant(String value){
        this.status = value;
    }
}
