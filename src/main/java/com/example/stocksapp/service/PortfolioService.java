package com.example.stocksapp.service;

import com.example.stocksapp.model.Portfolio;
import com.example.stocksapp.model.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PortfolioService {

    public List<String> simulatePortfolio(List<Map<String, Object>> stocksData, int months, boolean showInfo) {
        Portfolio portfolio = new Portfolio();

        for (Map<String, Object> stockData : stocksData) {
            Stock stock = new Stock(
                    (String) stockData.get("stockSymbol"),
                    (String) stockData.get("currency"),
                    ((Number) stockData.get("stockPrice")).doubleValue(),
                    ((Number) stockData.get("dividendYield")).doubleValue(),
                    ((Number) stockData.get("volatility")).doubleValue(),
                    ((Number) stockData.get("expectedReturn")).doubleValue(),
                    ((Number) stockData.get("totalShares")).doubleValue()
            );

            System.out.println("Created Stock: " + stock.getName() + " - Price: " + stock.getStockPrice() + " - Shares: " + stock.getTotalShares());

            portfolio.addStock(stock);
        }

        return portfolio.simulateMonths(months, showInfo);
    }

}
