package com.example.stocksapp.service;

import com.example.stocksapp.model.Person;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    // Method to deposit funds from investment to bank account
    public void depositToBank(Person person, double amount) {
        person.depositToBank(amount);
        System.out.println("Deposited " + amount + " from investment account to bank account.");
    }

    // Method to withdraw funds from bank account to investment account
    public void withdrawFromBank(Person person, double amount) {
        person.withdrawFromBank(amount);
        System.out.println("Withdrawn " + amount + " from bank account to investment account.");
    }
}
