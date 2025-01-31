package com.example.stocksapp.controller;

import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.dto.ClientRequest;
import com.example.stocksapp.model.dto.ClientResponse;
import com.example.stocksapp.service.TradingCompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/api/clients")  // My base URL
public class TradingCompanyController {

    @Autowired
    private TradingCompanyService tradingCompanyService;

    // Add a new client
    @PostMapping
    public Person addClient(@RequestBody ClientRequest clientRequest) {
        return tradingCompanyService.addClient(
                clientRequest.getName(),
                clientRequest.getEmail(),
                clientRequest.getBankAccountBalance(),
                clientRequest.getInvestmentAccountBalance()
        );
}

    @GetMapping
    public List<ClientResponse> getAllClients() {
        List<ClientResponse> response = new ArrayList<>();
        for (Person client : tradingCompanyService.getAllClients()) {
            response.add(new ClientResponse(
                    client.getName(),
                    client.getEmail(),
                    client.getBankAccount().getIban(),
                    client.getBankAccount().getBalance(),
                    client.getInvestmentAccount().getBalance()
            ));
        }
        return response;
    }

    // GET a client by email
    @GetMapping("/{email}")
    public Person getClientByEmail(@PathVariable String email) {
        return tradingCompanyService.getClientByEmail(email);
    }

    // UPDATE a client by email
    @PutMapping("/{email}")
    public Person updateClientByEmail(@PathVariable String email, @RequestBody Person updatedClient) {
        return tradingCompanyService.updateClientByEmail(email, updatedClient);
    }

    // DELETE a client by email
    @DeleteMapping("/{email}")
    public boolean deleteClientByEmail(@PathVariable String email) {
        return tradingCompanyService.deleteClientByEmail(email);
    }
}