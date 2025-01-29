package com.example.stocksapp.service;

import com.example.stocksapp.model.BankAccount;
import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.TradingCompany;
import com.example.stocksapp.model.Account;
import com.example.stocksapp.repositories.AccountRepository;
import com.example.stocksapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TradingCompanyService {

//  private TradingCompany tradingCompany = new TradingCompany(); // The data holder
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AccountRepository accountRepository;

//    public Person addClient(String name, String email, double bankAccountBalance, double investmentAccountBalance) {
//
//        BankAccount bankAccount = new BankAccount(bankAccountBalance);
//        Account investmentAccount = new Account(investmentAccountBalance, bankAccount);
//
//
//        Person person = new Person(name, email, bankAccount, investmentAccount);
//
//        tradingCompany.addClient(person);
//
//        return person;
//    }

public Person addClient(String name, String email, double bankAccountBalance, double investmentAccountBalance) {
    BankAccount bankAccount = new BankAccount(bankAccountBalance);
    Account investmentAccount = new Account(investmentAccountBalance, bankAccount);
    Person person = new Person(name, email, bankAccount, investmentAccount);

//    person.setInvestmentAccount(investmentAccount); // 🔹 Link Person to Account

    accountRepository.save(investmentAccount); // Save the investment account
    personRepository.save(person); // Save the person

    return person;
}

//    public List<Person> getAllClients() {
//        return tradingCompany.getAllClients();
//    }

public List<Person> getAllClients() {
    return personRepository.findAll();
}

//    public Person getClientByEmail(String email) {
//        return tradingCompany.getClientByEmail(email);
//    }

public Person getClientByEmail(String email) {
    return personRepository.findById(email).orElse(null); // Return null if not found
}

//    public Person updateClientByEmail(String email, Person updatedClient) {
//        Person client = tradingCompany.getClientByEmail(email);
//
//        if (client != null) {
//
//            if (updatedClient.getName() != null) {
//                client.setName(updatedClient.getName());
//            }
//
//            if (updatedClient.getBankAccount() != null) {
//                client.setBankAccount(updatedClient.getBankAccount());
//            }
//
//            if (updatedClient.getInvestmentAccount() != null) {
//                client.setInvestmentAccount(updatedClient.getInvestmentAccount());
//            }
//            return client;
//        }
//        return null; // If client not found
//    }

public Person updateClientByEmail(String email, Person updatedClient) {
    return personRepository.findById(email).map(client -> {
        if (updatedClient.getName() != null) {
            client.setName(updatedClient.getName());
        }
        if (updatedClient.getBankAccount() != null) {
            client.setBankAccount(updatedClient.getBankAccount());
        }
        if (updatedClient.getInvestmentAccount() != null) {
            client.setInvestmentAccount(updatedClient.getInvestmentAccount());
        }
        return personRepository.save(client); // Save the updated client
    }).orElse(null); // If not found, return null
}

//    public boolean deleteClientByEmail(String email) {
//        Person client = tradingCompany.getClientByEmail(email);
//        if (client != null) {
//
//            tradingCompany.getAllClients().remove(client);
//            return true;
//        }
//        return false; // If client not found
//    }

public boolean deleteClientByEmail(String email) {
    if (personRepository.existsById(email)) {
        personRepository.deleteById(email);
        return true;
    }
    return false;
}

//    public boolean deleteClientByEmail(String email) {
//        Optional<Person> clientOptional = personRepository.findById(email);
//        if (clientOptional.isPresent()) {
//            Person client = clientOptional.get();
//
//            if (client.getInvestmentAccount() != null) {
//                accountRepository.delete(client.getInvestmentAccount()); // Delete Account first
//            }
//
//            personRepository.delete(client); // Then delete Person
//            return true;
//        }
//        return false;
//    }

}
