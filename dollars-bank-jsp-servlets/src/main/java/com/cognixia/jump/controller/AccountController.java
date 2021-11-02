package com.cognixia.jump.controller;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Login;
import com.cognixia.jump.service.AccountService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public String viewLogin(Model model) {
        Login login = new Login();
        model.addAttribute("login", login);
        return "view-login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("login") Login login, Model model) {
        String email = login.getEmail();
        String password = login.getPassword();
        System.out.println(login.toString());
        if(accountService.login(email, password)) {
            System.out.println("Login accepted.");
            return "redirect:/account";
            // Account currentAccount = accountService.getCurrentAccount();
            // model.addAttribute("account", currentAccount);
            // return "view-account";
        }
        // the login was incorrect
        login = new Login();
        model.addAttribute("login", login);
        model.addAttribute("loginFailure", true);
        return "view-login";
    }

    @GetMapping("/account")
    public String viewAccount(Model model) {
        model.addAttribute("account", accountService.getCurrentAccount());
        return "view-account";
    }
    
}
