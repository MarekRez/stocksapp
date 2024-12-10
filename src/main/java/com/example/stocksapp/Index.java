package com.example.stocksapp;

import com.example.stocksapp.model.*;

public class Index {
    public static void main(String[] args) {

        // Creating stock objects with optional parameters (name, currency, stockPrice, dividendYield, volatility, and expectedReturn)
        Stock apple = new Stock(StockSymbol.AAPL, "USD", 150, 0.02, 0.6, 0.2, 0);
        Stock google = new Stock(StockSymbol.GOOGL, "USD", 2800, 0.20, 0.7, 0.3);
        Stock tesla = new Stock(StockSymbol.TSLA, "USD", 750, 0.15, 0.8, 0.1);

        // Creating bank account 1
        BankAccount bankAccount1 = new BankAccount(10000);
        System.out.println("Bank Account Balance: " + bankAccount1.getBalance());

        // Creating investment account 1
        Account investmentAccount1 = new Account(0, bankAccount1); // initial balance 0
        System.out.println("Investment Account Balance: " + investmentAccount1.getBalance());

        // Creating person and assigning the bank account and investment account
        Person marek = new Person("Marek Rezny", "rezny.marek@gmail.com", bankAccount1, investmentAccount1);

        // Performing some operations
        investmentAccount1.deposit(8000); // deposit 8000 into investment account
        marek.depositToBank(2000); // deposit 2000 back to the bank account

        System.out.println("-------------------");

        // Print the balances
        System.out.println("Bank Account Balance: " + bankAccount1.getBalance());
        System.out.println("Investment Account Balance: " + investmentAccount1.getBalance());
    }
}