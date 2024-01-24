package org.example;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private double balance;
    private AccountType accountType;
    private List<Transaction> transactions = new ArrayList<>();

    public Account(String accountNumber, AccountType accountType) {
        this.accountNumber = accountNumber;
        this.accountType = accountType;
    }

    public void deposit(double amount) {
        balance += amount;
        transactions.add(new Transaction(TransactionType.DEPOSIT, amount, 0));
    }

    public void withdraw(double amount) {
        if (balance < amount) {
            throw new IllegalArgumentException("Insufficient funds for withdrawal");
        }
        balance -= amount;
        transactions.add(new Transaction(TransactionType.WITHDRAWAL, amount, 0));
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public double getBalance() {
        return balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }
}

enum AccountType {
    SAVINGS,
    CHECKING
}
