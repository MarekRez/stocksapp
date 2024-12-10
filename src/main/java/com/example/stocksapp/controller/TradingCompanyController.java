package com.example.stocksapp.controller;

import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.dto.ClientRequest;
import com.example.stocksapp.service.TradingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")  // Base URL path for all client-related endpoints
public class TradingCompanyController {

    @Autowired
    private TradingCompanyService tradingCompanyService;

    // Add a new client
    @PostMapping
    public Person addClient(@RequestBody ClientRequest clientRequest) {
        return tradingCompanyService.addClient(clientRequest.getName(), clientRequest.getEmail(),
                clientRequest.getBankAccountBalance(), clientRequest.getInvestmentAccountBalance());
}

    // Get all clients
    @GetMapping
    public List<Person> getAllClients() {
        return tradingCompanyService.getAllClients();
    }

    // Get a client by email
    @GetMapping("/{email}")
    public Person getClientByEmail(@PathVariable String email) {
        return tradingCompanyService.getClientByEmail(email);
    }

    // Update a client by email
    @PutMapping("/{email}")
    public Person updateClientByEmail(@PathVariable String email, @RequestBody Person updatedClient) {
        return tradingCompanyService.updateClientByEmail(email, updatedClient);
    }

    // Delete a client by email
    @DeleteMapping("/{email}")
    public boolean deleteClientByEmail(@PathVariable String email) {
        return tradingCompanyService.deleteClientByEmail(email);
    }
}