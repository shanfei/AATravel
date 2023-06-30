package com.home.aatravel.web;

import com.home.aatravel.entity.User;
import com.home.aatravel.model.TransactionMemberDetail;
import com.home.aatravel.model.UserResponse;
import com.home.aatravel.model.UserSignupRequest;
import com.home.aatravel.service.UserService;
import com.home.aatravel.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users")
public class UserController {

    private PaymentService paymentService;

    private UserService userService;

    @GetMapping("/{uid}/projects/{pid}/detail")
    public List<TransactionMemberDetail> getTransactionMemberDetails(
            @PathVariable("uid") long uid,
            @PathVariable("pid") long pid
    ) throws Exception {
        return paymentService.calculateByUserAndProject(uid, pid);
    }

    @PostMapping("/signup")
    public UserResponse signup(@RequestBody UserSignupRequest userSignupRequest) {

        return userService.signup(userSignupRequest);

    }

}
