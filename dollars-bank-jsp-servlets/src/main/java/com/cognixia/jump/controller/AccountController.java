package com.cognixia.jump.controller;

import com.cognixia.jump.service.AccountService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping
    public String viewAccount(Model model) {
        model.addAttribute("account", accountService.getCurrentAccount());
        return "view-account";
    }
    
}
