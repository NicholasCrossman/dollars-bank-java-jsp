package com.cognixia.jump.model;

import java.util.Date;

public class Transaction {

    private double prevBalance;
    private double amount;
    private double newBalance;
    private String message;
    private Date date;


    public Transaction(double prevBalance, double amount, String message) {
        this.prevBalance = prevBalance;
        this.amount = amount;
        this.newBalance = prevBalance + amount;
        this.message = message;
        this.date = new Date();
    }

    public double getPrevBalance() {
        return this.prevBalance;
    }

    public void setPrevBalance(double prevBalance) {
        this.prevBalance = prevBalance;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getNewBalance() {
        return this.newBalance;
    }

    public void setNewBalance(double newBalance) {
        this.newBalance = newBalance;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{" +
            " prevBalance='" + getPrevBalance() + "'" +
            ", amount='" + getAmount() + "'" +
            ", newBalance='" + getNewBalance() + "'" +
            ", message='" + getMessage() + "'" +
            ", date='" + getDate() + "'" +
            "}";
    }

    
}
