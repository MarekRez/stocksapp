package com.example.stocksapp.model;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private List<Stock> stocks; // List of stocks
    private double balance;
    private BankAccount bankAccount;

    // Constructor to initialize the balance and bank account
    public Account(double initialBalance, BankAccount bankAccount) {
        this.stocks = new ArrayList<>(); // Initialize empty list for stocks
        this.balance = initialBalance;
        this.bankAccount = bankAccount;
    }

    // Getter for stocks
    public List<Stock> getStocks() {
        return this.stocks;
    }

    // Getter for balance
    public double getBalance() {
        return this.balance;
    }

    // Deposit money into the investment account
    public void deposit(double amount) {
        if (this.bankAccount.withdraw(amount)) { // If enough funds in the bank account
            this.balance += amount;
            System.out.println(String.format("Deposited %.2f to investment account. New balance: %.2f", amount, this.balance));
        } else {
            System.out.println("Deposit failed: Insufficient funds in bank account.");
        }
    }

    // Withdraw money from the investment account
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.bankAccount.deposit(amount);
            System.out.println(String.format("Withdrawn %.2f from account. New balance: %.2f", amount, this.balance));
            return true; // Successful withdrawal
        } else {
            System.out.println(String.format("Withdrawal failed: Insufficient funds. Current balance: %.2f", this.balance));
            return false; // Withdrawal failed
        }
    }
}
