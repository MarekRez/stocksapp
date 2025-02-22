package com.example.stocksapp.model;

import jakarta.persistence.*;

@Entity
public class Person {

    private String name;
    @Id
    private String email;
    @Embedded
    private BankAccount bankAccount; // bank account
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Account investmentAccount; // investment account
    public Person() {
    }

    public Person(String name, String email, BankAccount bankAccount, Account investmentAccount) {
        this.name = name;
        this.email = email;
        this.bankAccount = bankAccount;
        this.investmentAccount = investmentAccount;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getEmail() {
        return email;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount newBankAccount) {
        this.bankAccount = newBankAccount;
    }

    public Account getInvestmentAccount() {
        return investmentAccount;
    }

    public void setInvestmentAccount(Account newInvestmentAccount) {
        this.investmentAccount = newInvestmentAccount;
    }

    // deposit funds from investment account to bank account
    public void depositToBank(double amount) {
        if (amount <= this.investmentAccount.getBalance()) {
            this.investmentAccount.withdraw(amount); // calling withdraw from Account
            this.bankAccount.deposit(amount); // calling deposit from BankAccount
            System.out.println("Deposited " + String.format("%.2f", amount) + " to bank account " + this.bankAccount.getIban() + ". New bank balance: " + String.format("%.2f", this.bankAccount.getBalance()));
        } else {
            System.out.println("Deposit to bank failed: Insufficient funds in investment account.");
        }
    }

    // withdraw funds from bank account to investment account
    public void withdrawFromBank(double amount) {
        if (this.bankAccount.withdraw(amount)) {
            this.investmentAccount.deposit(amount);
            System.out.println("Withdrawn " + String.format("%.2f", amount) + " from bank account " + this.bankAccount.getIban() + ". New investment balance: " + String.format("%.2f", this.investmentAccount.getBalance()));
        } else {
            System.out.println("Withdrawal failed: Insufficient funds in bank account.");
        }
    }
}