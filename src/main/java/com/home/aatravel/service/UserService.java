package com.home.aatravel.service;

import com.home.aatravel.entity.User;
import com.home.aatravel.model.UserResponse;
import com.home.aatravel.model.UserSignupRequest;
import com.home.aatravel.repository.UserRepository;
import com.home.aatravel.web.exceptions.AccountExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User findByName(String name) {
        return this.userRepository.findByName(name).orElse(null);
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
