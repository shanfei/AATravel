package com.home.aatravel.web;

import com.home.aatravel.entity.User;
import com.home.aatravel.model.TransactionMemberDetail;
import com.home.aatravel.service.UserService;
import com.home.aatravel.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/users")
public class UserController {

    private PaymentService paymentService;

    private UserService memberService;

    @GetMapping("/{uid}/projects/{pid}/detail")
    public List<TransactionMemberDetail> getTransactionMemberDetails(
            @PathVariable("uid") long uid,
            @PathVariable("pid") long pid
    ) throws Exception {
        return paymentService.calculateByUserAndProject(uid, pid);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return memberService.save(user);
    }
}
