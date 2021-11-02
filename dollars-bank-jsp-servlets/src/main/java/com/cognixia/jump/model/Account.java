package com.cognixia.jump.model;

import java.util.ArrayList;

public class Account {

    private String name;
    private String email;
    private String address;
    private int accountId;
    private String password;
    private double balance;
    private ArrayList<Transaction> transactions;

    public Account() {
        this.name = "";
        this.email = "";
        this.address = "";
        this.accountId = -1;
        this.password = "";
        this.balance = 0.0;
    }
    
    public Account(String name, String email, String address, int accountId, String password, double balance) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.accountId = accountId;
        this.password = password;
        this.balance = balance;
        this.transactions = new ArrayList<>();
        Transaction initialBalance = new Transaction(0.0, balance, "Initial Balance");
        this.transactions.add(initialBalance);
    }
    
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAccountId() {
        return this.accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public double getBalance() {
        return this.balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public ArrayList<Transaction> getTransactions() {
        return this.transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction transaction(double amount, String message) {
        // make sure an overdraft does not occur
        if(balance + amount < 0) {
            return null;
        }
        else {
            Transaction transaction = new Transaction(balance, amount, message);
            balance += amount; // add or subtract the amount
            this.transactions.add(transaction);
            return transaction;
        }
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", email='" + getEmail() + "'" +
            ", address='" + getAddress() + "'" +
            ", accountId='" + getAccountId() + "'" +
            ", password='" + getPassword() + "'" +
            ", balance='" + getBalance() + "'" +
            ", transactions='" + getTransactions() + "'" +
            "}";
    }
}
