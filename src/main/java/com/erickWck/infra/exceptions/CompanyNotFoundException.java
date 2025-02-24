package com.erickWck.infra.exceptions;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
        super("Company not found");
    }

}
