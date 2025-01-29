package com.example.stocksapp.model;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinColumn(name = "account_id") // Foreign key column in the Stock table
    private List<Stock> stocks; // List of stocks

    @Column(name = "account_balance")
    private double balance;

    @Embedded
//    @Column(name = "bank_account")
    private BankAccount bankAccount;

    public Account(double initialBalance, BankAccount bankAccount) {
        this.stocks = new ArrayList<>();
        this.balance = initialBalance;
        this.bankAccount = bankAccount;
    }

    public Account() {
    }

    public List<Stock> getStocks() {
        return this.stocks;
    }

    public double getBalance() {
        return this.balance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (this.bankAccount.withdraw(amount)) {
            this.balance += amount;
            System.out.println(String.format("Deposited %.2f to investment account. New balance: %.2f", amount, this.balance));
        } else {
            System.out.println("Deposit failed: Insufficient funds in bank account.");
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            this.bankAccount.deposit(amount);
            System.out.println(String.format("Withdrawn %.2f from account. New balance: %.2f", amount, this.balance));
            return true;
        } else {
            System.out.println(String.format("Withdrawal failed: Insufficient funds. Current balance: %.2f", this.balance));
            return false;
        }
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addStock(Stock stock) {
        if (stock.getStockPrice() * stock.getTotalShares() > this.balance) {
            System.out.println("Insufficient funds to buy stock.");
            return;
        }
        this.balance -= stock.getStockPrice() * stock.getTotalShares();
        this.stocks.add(stock);
        System.out.println("Bought " + stock.getTotalShares() + " shares of " + stock.getName() +
                " for " + stock.getStockPrice() * stock.getTotalShares() + ". New balance: " + this.balance);

    }
}
