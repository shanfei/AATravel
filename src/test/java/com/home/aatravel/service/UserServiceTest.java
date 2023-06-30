package com.home.aatravel.service;

import com.home.aatravel.AaTravelApplication;
import com.home.aatravel.entity.User;
import com.home.aatravel.model.Email;
import com.home.aatravel.model.UserResponse;
import com.home.aatravel.model.UserSignupRequest;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class UserServiceTest {

    @Resource
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserService userService;

    @Test
    public void testSignup() {

        String password = RandomStringUtils.randomAlphabetic(10);

        UserSignupRequest userSignupRequest = new UserSignupRequest();
        userSignupRequest.setEmail(new Email("fei.shan", "gmail.com"));
        userSignupRequest.setPassword(password);

        UserResponse createdUser = userService.signup(userSignupRequest);

        assertNotNull(createdUser);
        assertEquals("fei.shan@gmail.com", createdUser.getEmail());
    }

}
