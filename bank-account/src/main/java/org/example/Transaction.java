package org.example;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime timestamp;
    private TransactionType type;
    private double amount;
    private double interestRate; // New field for interest transactions

    public Transaction(TransactionType type, double amount, double interestRate) {
        this.timestamp = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
        this.interestRate = interestRate;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public TransactionType getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getInterestRate() {
        return interestRate;
    }
}

enum TransactionType {
    DEPOSIT,
    WITHDRAWAL,
    TRANSFER_IN,
    TRANSFER_OUT,
    INTEREST
}

