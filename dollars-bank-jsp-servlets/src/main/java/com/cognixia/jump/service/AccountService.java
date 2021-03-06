package com.cognixia.jump.service;

import java.util.ArrayList;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Transaction;

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

    /**
     * Returns the current account. This will be null if the user is not logged in.
     * @return Account - The currently logged in account, or null if the user is not logged in.
     */
    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * Returns all accounts except the current account. This is used to show possible 
     * destinations for a funds transfer.
     * @return ArrayList<Account> - A collection of all accounts but the logged in account.
     */
    public ArrayList<Account> getAllAccounts() {
        ArrayList<Account> returnAccounts = new ArrayList<>();
        for(int i=0; i < accounts.size(); i++) {
            // if this account isn't the current account, add it to the return
            if(this.currentAccount.getAccountId() != accounts.get(i).getAccountId()) {
                returnAccounts.add(accounts.get(i));
            }
        }
        return returnAccounts;
    }

    /**
     * Allows the user to log in. Accepts an email and password, returning true if the login 
     * succeeds and false if it fails. Success will set the current account to the one with this 
     * email address.
     * @param email String - The user's unique email.
     * @param password String - The user's password.
     * @return boolean - True if the login succeeds, and false otherwise.
     */
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
     * Lets the user log out. Sets the current account to null, which disables most methods 
     * in this AccountService.
     */
    public void logout() {
        currentAccount = null;
    }

    public boolean changePassword(String oldPassword, String newPassword) {
        // make sure currentAccount is not null
        if(currentAccount == null) {
            return false;
        }
        // make sure the old password matches
        if(currentAccount.getPassword().equals(oldPassword)) {
            currentAccount.setPassword(newPassword);
            return true;
        }
        // the old password doesn't match
        return false;
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

    /**
     * Returns an account by its unique email. Returns null if no account 
     * is found.
     * @param email String - The account's unique email.
     * @return Account - The account with this email, or null if no account is found.
     */
    private Account getAccountByEmail(String email) {
        for(int i = 0; i < accounts.size(); i++) {
            Account next = accounts.get(i);
            if(next.getEmail().equals(email)) {
                return next;
            }
        }
        return null;
    }

    /**
     * Returns an account by its unique ID. Returns null if no account 
     * is found.
     * @param id int - The account's unique integer ID.
     * @return Account - The account with this email, or null if no account is found.
     */
    private Account getAccountById(int id) {
        for(int i = 0; i < accounts.size(); i++) {
            Account next = accounts.get(i);
            if(next.getAccountId() == id) {
                return next;
            }
        }
        return null;
    }

    /**
     * Performs a deposit on the current account. Accepts a double as the amount. 
     * Returns the Transaction on success, or null if no user is logged in. If a 
     * negative amount is entered, it will be converted to a positive amount.
     * @param amount double - The amount to be deposited.
     * @return Transaction - The resulting Transaction on success, or null on failure.
     */
    public Transaction deposit(double amount) {
        // if the currentAccount is null, return null
        if(currentAccount == null) {
            return null;
        }
        // the amount must be positive
        if(amount < 0) {
            // if the amount is negative, convert it to a positive value
            amount *= -1.0;
        }
        return transaction(currentAccount.getEmail(), amount, "Deposit");
    }

    /**
     * Performs a withdrawl on the current account. Accepts a double as the amount. 
     * Returns the Transaction on success, or null if no user is logged in or an overdraft 
     * occurs. If a positive amount is entered, it will be converted to a negative amount.
     * @param amount double - The amount to be withdrawn.
     * @return Transaction - The resulting Transaction on success, or null on failure.
     */
    public Transaction withdrawl(double amount) {
        // if the currentAccount is null, return null
        if(currentAccount == null) {
            return null;
        }
        // the amount must be negative
        if(amount > 0) {
            // if the amount is positive, convert it to a negative value
            amount *= -1.0;
        }
        return transaction(currentAccount.getEmail(), amount, "Withdrawl");
    }


    /**
     * Logs a transaction for the account identified by its email. Accepts a double amount 
     * and a message string to help identify the transaction's purpose. Returns null if the amount 
     * is zero or no account is found. A positive amount is a deposit, while a negative amount is a 
     * withdrawl.
     * @param email String - The account's unique email.
     * @param amount double - The amount of the transaction, positive or negative.
     * @param message String - A message to identify the transaction.
     * @return Transaction - The Transaction in the target account on success, and null on failure.
     */
    private Transaction transaction(String email, double amount, String message) {
        // if the amount is zero, return null
        if(amount == 0.0) {
            return null;
        }
        Account account = getAccountByEmail(email);
        if(account == null) {
            // no account found with that email
            return null;
        }
        // log the transaction and return it if successful
        return account.transaction(amount, message);
    }

    /**
     * Returns the last five transactions for the current account. If fewer than five 
     * exist, then all of them are returned.
     * @return ArrayList<Transaction> - The last five Transactions, or fewer if there are less than five.
     */
    public ArrayList<Transaction> lastFiveTransactions() {
        int numberOfTransactions = currentAccount.getTransactions().size();
        // if there are 5 or fewer transactions, return them all
        if(numberOfTransactions <= 5) {
            return currentAccount.getTransactions();
        }
        // otherwise, return only the 5 most recent
        ArrayList<Transaction> transactions = currentAccount.getTransactions();
        ArrayList<Transaction> returnTransactions = new ArrayList<>();
        int endIndex = transactions.size() - 1;

        for(int i = endIndex; i > endIndex - 5; i--) {
            returnTransactions.add(transactions.get(i));
        }
        return returnTransactions;
    }

    /**
     * Transfers money from the current account to a target account. The money is 
     * withdrawn from the current account and deposited in the target account. 
     * A null is returned if an overdraft occurs, or the user is not logged in.
     * @param targetAccountId int - The integer ID of the target account.
     * @param amount double - The amount to be transferred.
     * @return Transaction - The successful Transaction of the destination account 
     *                      if the operation succeeds, and null if there was an error.
     */
    public Transaction transfer(int targetAccountId, double amount) {
        // if the currentAccount is null, return null
        if(currentAccount == null) {
            return null;
        }
        // the amount must be positive
        if(amount < 0) {
            // if the amount is negative, convert it to a positive value
            amount *= -1.0;
        }
        // if the amount is zero, cancel the transfer
        if(amount == 0.0) {
            return null;
        }
        // get the target account
        Account target = getAccountById(targetAccountId);
        // get the money from the current account
        // if this is an overdraft, this will return null
        Transaction source = transaction(currentAccount.getEmail(), amount * -1, "Transfer to " + target.getName());
        if(source == null) {
            // cancel the transfer. The current account has insufficient funds.
            return null;
        }
        Transaction destination = transaction(target.getEmail(), amount, "Transfer from " + currentAccount.getName());
        return destination;
    }
}
