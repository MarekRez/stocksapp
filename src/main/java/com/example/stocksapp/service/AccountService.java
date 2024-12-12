package com.example.stocksapp.service;

import com.example.stocksapp.model.Account;
import org.springframework.stereotype.Service;

// unused for now

@Service
public class AccountService {

    public void depositToAccount(Account account, double amount) {
        if (amount > 0) {
            account.setBalance(account.getBalance() + amount);
            System.out.println("Deposited " + amount + " into investment account.");
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

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
}
