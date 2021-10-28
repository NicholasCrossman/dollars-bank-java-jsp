package com.cognixia.jump.controller;

import com.cognixia.jump.service.AccountService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
    
}
