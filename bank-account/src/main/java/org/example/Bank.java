package org.example;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private Map<String, Account> accounts;
    private AccountService accountService;

    public Bank(AccountService accountService) {
        this.accounts = new HashMap<>();
        this.accountService = accountService;
    }

    public void createAccount(String accountNumber, AccountType accountType) {
        if (accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account already exists");
        }
        accounts.put(accountNumber, new Account(accountNumber, accountType));
    }

    public void deposit(String accountNumber, double amount) {
        validateAmount(amount);
        Account account = getAccount(accountNumber);
        account.deposit(amount);
    }

    public void withdraw(String accountNumber, double amount) {
        validateAmount(amount);
        Account account = getAccount(accountNumber);
        account.withdraw(amount);
    }

    public double getBalance(String accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    public double calculateInterest(String accountNumber) {
        double interestRate = accountService.getInterestRate(accountNumber);
        Account account = getAccount(accountNumber);
        double balance = account.getBalance();
        return balance * interestRate;
    }

    public List<Transaction> getAccountTransactions(String accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getTransactions();
    }

    public void performTransfer(String sourceAccount, String targetAccount, double amount) {
        validateAmount(amount);
        Account source = getAccount(sourceAccount);
        Account target = getAccount(targetAccount);

        if (source.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds for transfer");
        }

        source.withdraw(amount);
        target.deposit(amount);

        source.getTransactions().add(new Transaction(TransactionType.TRANSFER_OUT, amount, 0));
        target.getTransactions().add(new Transaction(TransactionType.TRANSFER_IN, amount, 0));
    }

    public void addInterest(String accountNumber, double interestRate) {
        validateInterestRate(interestRate);
        Account account = getAccount(accountNumber);
        double balanceBeforeInterest = account.getBalance();
        double interest = account.getBalance() * interestRate;
        account.deposit(interest);
        account.getTransactions().add(new Transaction(TransactionType.INTEREST, interest, interestRate));
        account.getTransactions().add(new Transaction(TransactionType.INTEREST, balanceBeforeInterest, interestRate));
    }

    private Account getAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            throw new IllegalArgumentException("Account not found");
        }
        return accounts.get(accountNumber);
    }

    private void validateAmount(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than 0");
        }
    }

    private void validateInterestRate(double interestRate) {
        if (interestRate <= 0 || interestRate > 1) {
            throw new IllegalArgumentException("Interest rate must be between 0 and 1");
        }
    }
}

