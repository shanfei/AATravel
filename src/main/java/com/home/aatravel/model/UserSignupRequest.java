package com.home.aatravel.model;

import lombok.Data;

@Data
public class UserSignupRequest {

    private Email email;

    private String password;
}
