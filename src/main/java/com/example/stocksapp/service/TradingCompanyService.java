package com.example.stocksapp.service;

import com.example.stocksapp.model.BankAccount;
import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.TradingCompany;
import com.example.stocksapp.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingCompanyService {

    private TradingCompany tradingCompany = new TradingCompany(); // The data holder for clients

    // Add a new client
    public Person addClient(String name, String email, double bankAccountBalance, double investmentAccountBalance) {
        // Create the bank account and investment account
        BankAccount bankAccount = new BankAccount(bankAccountBalance);
        Account investmentAccount = new Account(investmentAccountBalance, bankAccount);

        // Create the new Person (client)
        Person person = new Person(name, email, bankAccount, investmentAccount);

        // Add the client to the TradingCompany object
        tradingCompany.addClient(person);

        return person;
    }

    // Get all clients
    public List<Person> getAllClients() {
        return tradingCompany.getAllClients();  // Fetching all clients from TradingCompany
    }

    // Get a client by email
    public Person getClientByEmail(String email) {
        return tradingCompany.getClientByEmail(email);  // Fetching a client by email
    }

    // Update a client by email
    public Person updateClientByEmail(String email, Person updatedClient) {
        Person client = tradingCompany.getClientByEmail(email);

        if (client != null) {
            // Update client properties
            if (updatedClient.getName() != null) {
                client.setName(updatedClient.getName());
            }

            if (updatedClient.getBankAccount() != null) {
                client.setBankAccount(updatedClient.getBankAccount());
            }

            if (updatedClient.getInvestmentAccount() != null) {
                client.setInvestmentAccount(updatedClient.getInvestmentAccount());
            }
            return client;
        }
        return null; // If client not found
    }

    // Delete a client by email
    public boolean deleteClientByEmail(String email) {
        Person client = tradingCompany.getClientByEmail(email);
        if (client != null) {
            // Remove client from the TradingCompany list
            tradingCompany.getAllClients().remove(client);
            return true;
        }
        return false; // If client not found
    }
}
