package com.cognixia.jump.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AccountTest {

    
    @Test
    public void initialBalanceTest() {
        double balance = 200.0;
        Account account = new Account("Tester", "test2@gmail.com", "placeholder", 1234567, "test", balance);

        assertEquals(balance, account.getBalance(), 1e-15);
    }

    @Test
    public void initialBalanceTransactionTest() {
        double balance = 200.0;
        Account account = new Account("Tester", "test2@gmail.com", "placeholder", 1234567, "test", balance);

        Transaction initialTransaction = account.getTransactions().get(0);
        assertEquals(balance, initialTransaction.getNewBalance(), 1e-15);
    }

    @Test
    public void transactionTest() {
        double initialBalance = 300.0;
        Account account = new Account("Tester", "test2@gmail.com", "placeholder", 1234567, "test", initialBalance);
        double withdraw = 150.0;
        double newBalance = initialBalance - withdraw;
        account.transaction(withdraw * -1, "Test withdrawl");

        Transaction testWithdrawlTransaction = account.getTransactions().get(1);
        assertEquals(newBalance, testWithdrawlTransaction.getNewBalance(), 1e-15);
    }
}

