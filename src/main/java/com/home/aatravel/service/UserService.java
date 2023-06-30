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



}
