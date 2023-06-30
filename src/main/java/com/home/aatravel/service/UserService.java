package com.home.aatravel.service;

import com.home.aatravel.entity.User;
import com.home.aatravel.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }

    public User findByName(String name) {
        return this.userRepository.findByName(name).orElse(null);
    }

}
