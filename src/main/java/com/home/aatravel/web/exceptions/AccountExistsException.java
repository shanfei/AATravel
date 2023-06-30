package com.home.aatravel.web.exceptions;

public class AccountExistsException extends BadRequestException {

    public AccountExistsException(String email) {
        super(String.format("account with email %s has been registered.", email));
    }

}
