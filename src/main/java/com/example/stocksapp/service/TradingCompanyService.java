package com.example.stocksapp.service;

import com.example.stocksapp.model.BankAccount;
import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.TradingCompany;
import com.example.stocksapp.model.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingCompanyService {

    private TradingCompany tradingCompany = new TradingCompany(); // The data holder

    public Person addClient(String name, String email, double bankAccountBalance, double investmentAccountBalance) {

        BankAccount bankAccount = new BankAccount(bankAccountBalance);
        Account investmentAccount = new Account(investmentAccountBalance, bankAccount);


        Person person = new Person(name, email, bankAccount, investmentAccount);

        tradingCompany.addClient(person);

        return person;
    }

    public List<Person> getAllClients() {
        return tradingCompany.getAllClients();
    }

    public Person getClientByEmail(String email) {
        return tradingCompany.getClientByEmail(email);
    }

    public Person updateClientByEmail(String email, Person updatedClient) {
        Person client = tradingCompany.getClientByEmail(email);

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
        return null; // If client not found
    }

    public boolean deleteClientByEmail(String email) {
        Person client = tradingCompany.getClientByEmail(email);
        if (client != null) {

            tradingCompany.getAllClients().remove(client);
            return true;
        }
        return false; // If client not found
    }
}
