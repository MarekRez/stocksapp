package com.example.stocksapp.model;
import java.util.Random;

public class BankAccount {
    private String iban;
    private double balance;

    public BankAccount(double initialBalance) {
        this.iban = generateIBAN(); // Generates a new IBAN
        this.balance = initialBalance;
    }

    private String generateIBAN() {
        String characters = "0123456789000000";
        StringBuilder iban = new StringBuilder("SK-");

        Random rand = new Random();
        for (int i = 0; i < 16; i++) {
            iban.append(characters.charAt(rand.nextInt(characters.length())));
        }

        return iban.toString();
    }

    // Deposit method to add funds to the account
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("Deposited " + String.format("%.2f", amount) + " to bank account " + this.iban + ". New balance: " + String.format("%.2f", this.balance));
        } else {
            System.out.println("Deposit failed: Invalid amount. Amount must be positive.");
        }
    }

    // Withdraw method to deduct funds from the account
    public boolean withdraw(double amount) {
        if (amount <= this.balance) {
            this.balance -= amount;
            System.out.println("Withdrawn " + String.format("%.2f", amount) + " from bank account " + this.iban + ". New balance: " + String.format("%.2f", this.balance));
            return true;
        } else {
            System.out.println("Withdrawal failed: Insufficient funds in bank account " + this.iban + ". Current balance: " + String.format("%.2f", this.balance));
            return false;
        }
    }

    public double getBalance() {
        return this.balance;
    }

    public String getIban() {
        return this.iban;
    }
}