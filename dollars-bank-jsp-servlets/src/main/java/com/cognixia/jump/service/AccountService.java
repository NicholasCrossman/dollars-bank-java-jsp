package com.cognixia.jump.service;

import java.util.ArrayList;

import com.cognixia.jump.model.Account;

import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    
    private Account currentAccount;
    private ArrayList<Account> accounts;

    public AccountService() {
        this.currentAccount = null;
        this.accounts = new ArrayList<>();
        Account test = new Account("Test", "test@gmail.com", "1234 3rd Street", 12345, "test", 50.00);
        this.accounts.add(test);
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    public boolean login(String email, String password) {
        if(!emailExists(email)) {
            // the email is incorrect
            return false;
        }
        Account account = getAccountByEmail(email);
        // check if the password is a match
        if(account.getPassword().equals(password)) {
            this.currentAccount = account;
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Creates a new Account and adds it to the accounts collection. Requires a name, email, 
     * address, password, and initial balance. It will return null if the email is not unique 
     * or the balance is less than 0.
     * @param name String - The customer's name.
     * @param email String - The customer's email, which much be unique.
     * @param address String - The customer's address.
     * @param password String - The customer's password.
     * @param balance double - The account's initial balance, which must be positive or zero.
     * @return Account - The new Account which has been created, or null if the email is already in use 
     *                  or the initial balance is negative.
     */
    public Account addAccount(String name, String email, String address, String password, double balance) {
        int id = randomId();
        // if the email already exists, return null
        if(emailExists(email)) {
            return null;
        }
        // if the balance is negative, return null
        if(balance < 0) {
            return null;
        }
        Account newAccount = new Account(name, email, address, id, password, balance);
        accounts.add(newAccount);
        return newAccount;
    }

    /**
     * Generates a random ID for an Account's ID, and makes sure it isn't already in use.
     * @return int - A random int between 0 and 1000000
     */
    private int randomId() {
        int randomId = RandomUtils.nextInt(0, 1000000);
        while(idExists(randomId)) {
            randomId = RandomUtils.nextInt(0, 1000000);
        }
        return randomId;
    }

    /**
     * Used during new Account creation check if the random ID is unique.
     * @param id int - The integer ID to be checked.
     * @return boolean - True if an ID is found, and false otherwise.
     */
    private boolean idExists(int id) {
        for(int i = 0; i < accounts.size(); i++) {
            Account next = accounts.get(i);
            if(next.getAccountId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * Used during new Account creation and login to check if the user's email exists.
     * @param email String - The integer ID to be checked.
     * @return boolean - True if an email is found, and false otherwise.
     */
    private boolean emailExists(String email) {
        for(int i = 0; i < accounts.size(); i++) {
            Account next = accounts.get(i);
            if(next.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private Account getAccountByEmail(String email) {
        for(int i = 0; i < accounts.size(); i++) {
            Account next = accounts.get(i);
            if(next.getEmail().equals(email)) {
                return next;
            }
        }
        return null;
    }
}
