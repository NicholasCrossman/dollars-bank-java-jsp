package com.cognixia.jump.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.cognixia.jump.model.Transaction;

import org.junit.Test;

public class AccountServiceTest {
    
    @Test
    public void testLogin() {
        String email = "test4@gmail.com";
        String password = "test";
        AccountService service = new AccountService();
        service.addAccount("Test", email, "placeholder", password, 200.0);
        boolean loginSuccess = service.login(email, password);
        assertTrue(loginSuccess);
    }

    @Test
    public void testDeposit() {
        String email = "test4@gmail.com";
        String password = "test";
        double initialBalance = 200.0;
        AccountService service = new AccountService();
        service.addAccount("Test", email, "placeholder", password, initialBalance);
        // login to set the currentAccount
        service.login(email, password);
        double depositAmount = 100.0;
        service.deposit(depositAmount);

        // assert the new balance
        double actualBalance = service.getCurrentAccount().getBalance();
        double expectedBalance = initialBalance + depositAmount;
        assertEquals(expectedBalance, actualBalance, 1e-15);
    }

    @Test
    public void testWithdrawl() {
        String email = "test4@gmail.com";
        String password = "test";
        double initialBalance = 200.0;
        AccountService service = new AccountService();
        service.addAccount("Test", email, "placeholder", password, initialBalance);
        // login to set the currentAccount
        service.login(email, password);
        double withdrawlAmount = 100.0;
        service.withdrawl(withdrawlAmount);

        // assert the new balance
        double actualBalance = service.getCurrentAccount().getBalance();
        double expectedBalance = initialBalance - withdrawlAmount;
        assertEquals(expectedBalance, actualBalance, 1e-15);
    }

    @Test
    public void testGetOneTransaction() {
        AccountService service = new AccountService();
        service.addAccount("Tester", "test3@gmail.com", "placeholder", "test", 400.0);
        // login to set the currentAccount
        service.login("test3@gmail.com", "test");
        // the initial balance transaction will be there already
        ArrayList<Transaction> returnedTransactions = service.lastFiveTransactions();
        Transaction trans = returnedTransactions.get(0);
        assertEquals(1, returnedTransactions.size());
        assertEquals(400.0, trans.getAmount(), 1e-15);
    }

    @Test
    public void testGetFiveTransactions() {
        AccountService service = new AccountService();
        service.addAccount("Tester", "test3@gmail.com", "placeholder", "test", 400.0);
        // login to set the currentAccount
        service.login("test3@gmail.com", "test");
        // the initial balance transaction will be there already
        // make 4 more, +200 in total
        service.deposit(100.0);
        service.withdrawl(50.0);
        service.deposit(50.0);
        service.deposit(100.0);

        ArrayList<Transaction> returnedTransactions = service.lastFiveTransactions();
        int numberOfTransactions = returnedTransactions.size();
        assertEquals(5, numberOfTransactions);
    }
}
