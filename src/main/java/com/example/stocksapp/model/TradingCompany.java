package com.example.stocksapp.model;

import java.util.ArrayList;
import java.util.List;

public class TradingCompany {
    private List<Person> clients = new ArrayList<>(); // List of clients (Person objects)

    // Add a new client
    public void addClient(Person newClient) {
        this.clients.add(newClient);
    }

    // Get all clients
    public List<Person> getAllClients() {
        return this.clients;
    }

    // Get client by email
    public Person getClientByEmail(String email) {
        return this.clients.stream()
                .filter(client -> client.getEmail().equals(email))
                .findFirst()
                .orElse(null); // Return null if no client is found
    }
}
