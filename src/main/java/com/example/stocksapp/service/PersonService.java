package com.example.stocksapp.service;

import com.example.stocksapp.model.Person;
import com.example.stocksapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public boolean doesEmailExist(String email) {
        return personRepository.existsByEmail(email);
    }
    public void depositToBank(Person person, double amount) {
        person.depositToBank(amount);
        System.out.println("Deposited " + amount + " from investment account to bank account.");
    }

    public void withdrawFromBank(Person person, double amount) {
        person.withdrawFromBank(amount);
        System.out.println("Withdrawn " + amount + " from bank account to investment account.");
    }
}
