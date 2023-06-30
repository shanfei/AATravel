package com.home.aatravel.web;


import com.home.aatravel.entity.User;
import com.home.aatravel.model.UserResponse;
import com.home.aatravel.model.UserSigninRequest;
import com.home.aatravel.model.UserSignupRequest;
import com.home.aatravel.service.AuthService;
import com.home.aatravel.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/v1/auth")
public class AuthController {

    @Resource
    private AuthService authService;

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest userSignupRequest) {

        return authService.signup(userSignupRequest);

    }

    @PostMapping("/signin")
    public UserResponse signin(@RequestBody UserSigninRequest userSigninRequest) {

        return authService.signin(userSigninRequest);

    }
}
