package com.example.stocksapp.service;

import com.example.stocksapp.model.Account;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    // Method to deposit money into an account
    public void depositToAccount(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited " + amount + " into investment account.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    // Method to withdraw money from an account
    public boolean withdrawFromAccount(Account account, double amount) {
        if (account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            System.out.println("Withdrew " + amount + " from investment account.");
            return true;
        } else {
            System.out.println("Insufficient funds.");
            return false;
        }
    }

    // Additional methods for account-related operations can go here
}
