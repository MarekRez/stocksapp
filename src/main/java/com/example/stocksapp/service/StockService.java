package com.example.stocksapp.service;

import com.example.stocksapp.exception.ResourceNotFoundException;
import com.example.stocksapp.model.Account;
import com.example.stocksapp.model.Person;
import com.example.stocksapp.model.Stock;
import com.example.stocksapp.model.StockSymbol;
import com.example.stocksapp.repositories.AccountRepository;
import com.example.stocksapp.repositories.PersonRepository;
import com.example.stocksapp.repositories.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

// unused for now

@Service
public class StockService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private StockRepository stockRepository;

    public Stock buyStock(String email, StockSymbol stockSymbol, String currency, double stockPrice,
                          double dividendYield, double volatility, double expectedReturn, int quantity) {

        Person person = personRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Person with email " + email + " not found"));

        Account investmentAccount = person.getInvestmentAccount();
        double totalCost = stockPrice * quantity;

        if (investmentAccount.getBalance() < totalCost) {
            throw new IllegalArgumentException("Insufficient balance to buy stock");
        }

        investmentAccount.setBalance(investmentAccount.getBalance() - totalCost);

        // Check if the user already owns this stock
        Optional<Stock> existingStock = investmentAccount.getStocks().stream()
                .filter(stock -> stock.getName() == stockSymbol && stock.getCurrency().equals(currency))
                .findFirst();

        if (existingStock.isPresent()) {
            Stock stock = existingStock.get();
            stock.setTotalShares(stock.getTotalShares() + quantity);
            stockRepository.save(stock);
            accountRepository.save(investmentAccount);
            return stock;
        } else {
            Stock stock = new Stock(stockSymbol, currency, stockPrice, dividendYield, volatility, expectedReturn, quantity);
            investmentAccount.getStocks().add(stock);
            stockRepository.save(stock);
            accountRepository.save(investmentAccount);
            return stock;
        }
    }

    public void sellStock(String email, StockSymbol stockSymbol, int quantity) {
        Person person = personRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Person with email " + email + " not found"));

        Account investmentAccount = person.getInvestmentAccount();
        Optional<Stock> stockOptional = investmentAccount.getStocks().stream()
                .filter(stock -> stock.getName() == stockSymbol)
                .findFirst();

        if (stockOptional.isEmpty()) {
            throw new IllegalArgumentException("Stock not found in investment account");
        }

        Stock stock = stockOptional.get();

        if (stock.getTotalShares() < quantity) {
            throw new IllegalArgumentException("Not enough shares to sell");
        }

        double totalValue = stock.getStockPrice() * quantity;
        stock.setTotalShares(stock.getTotalShares() - quantity);

        if (stock.getTotalShares() == 0) {
            investmentAccount.getStocks().remove(stock);
            stockRepository.delete(stock);
        } else {
            stockRepository.save(stock);
        }

        // Adding money back
        investmentAccount.setBalance(investmentAccount.getBalance() + totalValue);
        accountRepository.save(investmentAccount);
    }

    public List<Map<String, Object>> getAllStocks() {
        return personRepository.findAll().stream()
                .flatMap(person -> person.getInvestmentAccount().getStocks().stream()
                        .map(stock -> {
                            Map<String, Object> stockMap = new HashMap<>();
                            stockMap.put("email", person.getEmail());
                            stockMap.put("stockSymbol", stock.getName());
                            stockMap.put("shares", stock.getTotalShares());
                            return stockMap;
                        })
                )
                .collect(Collectors.toList());
    }

    public List<Stock> getStocksByPerson(String email) {
        Person person = personRepository.findById(email)
                .orElseThrow(() -> new ResourceNotFoundException("Person with email " + email + " not found"));

        return person.getInvestmentAccount().getStocks();
    }
}
