package com.example.stocksapp.model;

import java.util.ArrayList;
import java.util.List;

public class TradingCompany {
    private List<Person> clients = new ArrayList<>(); // List of Person objects

    // Method to add a new client
    public Person addClient(Person newClient) {
        this.clients.add(newClient);
        return newClient;
    }

    // Method to get all clients
    public List<Person> getAllClients() {
        return this.clients;
    }

    // Method to get a client by email
    public Person getClientByEmail(String email) {
        return this.clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .orElse(null); // If no client is found, return null
    }

    // Method to update a client by email
    public Person updateClientByEmail(String email, UpdatedClient updatedClient) {
        Person client = getClientByEmail(email);

        if (client != null) {
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
        return null; // Client not found
    }

    // Method to delete a client by email
    public boolean deleteClientByEmail(String email) {
        int initialLength = this.clients.size();
        this.clients.removeIf(client -> client.getEmail().equals(email)); // Remove client by email
        return this.clients.size() < initialLength; // If the size has decreased, client was deleted
    }

    // Inner class to represent updated client information
    public static class UpdatedClient {
        private String name;
        private BankAccount bankAccount;
        private Account investmentAccount;

        // Getters and setters for the updated fields
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BankAccount getBankAccount() {
            return bankAccount;
        }

        public void setBankAccount(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public Account getInvestmentAccount() {
            return investmentAccount;
        }

        public void setInvestmentAccount(Account investmentAccount) {
            this.investmentAccount = investmentAccount;
        }
    }
}