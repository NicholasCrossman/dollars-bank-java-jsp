package com.cognixia.jump.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import com.cognixia.jump.model.Account;
import com.cognixia.jump.model.Transaction;

import org.junit.Test;

public class AccountServiceTest {
    
    @Test
    public void testAddAccount() {
        AccountService service = new AccountService();
        String name = "John Doe";
        String email = "jdoe@gmail.com";
        String address = "1234 S 43rd Street, City, State";
        String password = "test";
        double balance = 200.0;

        Account test = service.addAccount(name, email, address, password, balance);
        // test the field values
        assertEquals(name, test.getName());
        assertEquals(email, test.getEmail());
        assertEquals(address, test.getAddress());
        assertEquals(password, test.getPassword());
        assertEquals(balance, test.getBalance(), 1e-15);
    }

    @Test
    public void testAddAccountEmailInUse() {
        AccountService service = new AccountService();
        String name = "John Doe";
        String email = "jdoe@gmail.com";
        String address = "1234 S 43rd Street, City, State";
        String password = "test";
        double balance = 200.0;

        Account original = service.addAccount(name, email, address, password, balance);
        // reuse all attributes, because only email is important for this test
        Account test = service.addAccount(name, email, address, password, balance);
        // an account with this email already exists, so return null
        assertNull(test);        
    }

    @Test
    public void testAddAccountNegativeBalance() {
        AccountService service = new AccountService();
        String name = "John Doe";
        String email = "jdoe@gmail.com";
        String address = "1234 S 43rd Street, City, State";
        String password = "test";
        double balance = -200.0;

        Account test = service.addAccount(name, email, address, password, balance);
        // a new account cannot have a negative balance, so return null
        assertNull(test);        
    }

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

    @Test
    public void testGetMoreThanFiveTransactions() {
        AccountService service = new AccountService();
        service.addAccount("Tester", "test3@gmail.com", "placeholder", "test", 400.0);
        // login to set the currentAccount
        service.login("test3@gmail.com", "test");
        // the initial balance transaction will be there already
        // make 5 more, 6 in total
        service.deposit(100.0);
        service.withdrawl(50.0);
        service.deposit(50.0);
        service.deposit(100.0);
        service.withdrawl(20.0);

        ArrayList<Transaction> returnedTransactions = service.lastFiveTransactions();
        int numberOfTransactions = returnedTransactions.size();
        // make sure only 5 are being sent back
        assertEquals(5, numberOfTransactions);
    }

    @Test
    public void testTransfer() {
        AccountService service = new AccountService();
        double initialBalance = 400.0;
        Account source = service.addAccount("Tester 1", "test1@gmail.com", "placeholder", "test", initialBalance);
        Account destination = service.addAccount("Tester 2", "test2@gmail.com", "placeholder", "test", initialBalance);
        
        double amount = 100.0;
        // set source as the currentAccount
        service.login("test1@gmail.com", "test");
        // perform the transfer
        Transaction trans = service.transfer(destination.getAccountId(), amount);
        // check that trans is not null
        assertNotNull(trans);
        // check the balance of both accounts
        double expectedSourceBalance = initialBalance - amount;
        double expectedDestinationBalance = initialBalance + amount;

        assertEquals(expectedSourceBalance, source.getBalance(), 1e-15);
        assertEquals(expectedDestinationBalance, destination.getBalance(), 1e-15);

        // check that both accounts now have 2 transactions: the initial balance and the transfer
        assertEquals(2, source.getTransactions().size());
        assertEquals(2, destination.getTransactions().size());
    }

    @Test
    public void testTransferNoCurrentAccount() {
        AccountService service = new AccountService();
        double initialBalance = 400.0;
    
        Account destination = service.addAccount("Tester 2", "test2@gmail.com", "placeholder", "test", initialBalance);
        
        double amount = 100.0;
        //no current account set
        // perform the transfer
        Transaction trans = service.transfer(destination.getAccountId(), amount);
        // no account is logged in, so it will return null
        assertNull(trans);
    }

    @Test
    public void testTransferZeroAmount() {
        AccountService service = new AccountService();
        double initialBalance = 400.0;
        Account source = service.addAccount("Tester 1", "test1@gmail.com", "placeholder", "test", initialBalance);
        Account destination = service.addAccount("Tester 2", "test2@gmail.com", "placeholder", "test", initialBalance);
        
        double amount = 0;
        // set source as the currentAccount
        service.login("test1@gmail.com", "test");
        // perform the transfer
        Transaction trans = service.transfer(destination.getAccountId(), amount);
        // the amount is zero, so it will return null
        assertNull(trans);
    }

    @Test
    public void testTransferOverdraft() {
        AccountService service = new AccountService();
        double initialBalance = 400.0;
        Account source = service.addAccount("Tester 1", "test1@gmail.com", "placeholder", "test", initialBalance);
        Account destination = service.addAccount("Tester 2", "test2@gmail.com", "placeholder", "test", initialBalance);
        
        double amount = 500.0;
        // set source as the currentAccount
        service.login("test1@gmail.com", "test");
        // perform the transfer
        Transaction trans = service.transfer(destination.getAccountId(), amount);
        // the amount is more than source has, so it will return null
        assertNull(trans);
    }
}
