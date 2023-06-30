package com.home.aatravel.service;


import com.home.aatravel.entity.User;
import com.home.aatravel.model.UserResponse;
import com.home.aatravel.model.UserSigninRequest;
import com.home.aatravel.model.UserSignupRequest;
import com.home.aatravel.repository.UserRepository;
import com.home.aatravel.web.exceptions.AccountExistsException;
import jakarta.annotation.Resource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Resource
    private UserRepository userRepository;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private AuthenticationManager authenticationManager;

    public UserResponse signin(UserSigninRequest userSigninRequest) {
        Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(
                userSigninRequest.getUsername(),
                userSigninRequest.getPassword()
        ) );

        return Optional.of( (User) authentication.getPrincipal() )
                .map( user -> new UserResponse(user.getId(), user.getEmail(), user.getUsername()))
                .get();
    }

    public UserResponse signup(UserSignupRequest userSignupRequest) {

        // 1. check if user has been created....
        if (userRepository.existsUserByEmail(userSignupRequest.getEmail().toString())) {
            throw new AccountExistsException(userSignupRequest.getEmail().toString());
        }

        // 2. encode password
        userSignupRequest.setPassword(passwordEncoder.encode(userSignupRequest.getPassword()));

        return Optional.of(userRepository.save(new User(userSignupRequest)))
                .map( user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setUsername(user.getUsername());
                    response.setEmail(user.getEmail());
                    return response;
                })
                .get();

    }


}
