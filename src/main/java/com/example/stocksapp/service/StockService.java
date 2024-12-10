package com.example.stocksapp.service;

import com.example.stocksapp.model.Stock;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    // Method to buy stock
    public void buyStock(Stock stock, int sharesToBuy) {
        // Logic to update stock shares and handle transaction
        stock.setTotalShares(stock.getTotalShares() + sharesToBuy);
        System.out.println("Bought " + sharesToBuy + " shares of " + stock.getName());
    }

    // Method to sell stock
    public void sellStock(Stock stock, int sharesToSell) {
        if (stock.getTotalShares() >= sharesToSell) {
            stock.setTotalShares(stock.getTotalShares() - sharesToSell);
            System.out.println("Sold " + sharesToSell + " shares of " + stock.getName());
        } else {
            System.out.println("Insufficient shares to sell");
        }
    }
}
