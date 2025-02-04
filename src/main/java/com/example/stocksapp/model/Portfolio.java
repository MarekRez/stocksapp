package com.example.stocksapp.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private List<Stock> stocks = new ArrayList<>();

    public void addStock(Stock stock) {
        this.stocks.add(stock);
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public List<String> simulateMonths(int months, boolean showInfo) {
        List<String> simulationResults = new ArrayList<>();
        simulationResults.add("\n--- Portfolio Simulation for " + months + " months ---\n");

        for (int month = 1; month <= months; month++) {
            if (showInfo) {
                simulationResults.add("Month " + month + ":");
            }

            double totalBalance = 0;

            for (Stock stock : stocks) {
                Stock.StockResult result = stock.simulateMonth();

                double monthPrice = result.getMonthPrice();
                double totalShares = result.getTotalShares();
                double balance = result.getBalance();

                if (showInfo) {
                    simulationResults.add("  " + stock.getName() + ":");
                    simulationResults.add("    Stock Price: " + String.format("%.2f", monthPrice) + " " + stock.getCurrency());
                    simulationResults.add("    Total Shares: " + String.format("%.4f", totalShares));
                    simulationResults.add("    Balance: " + String.format("%.2f", balance) + " " + stock.getCurrency());
                }

                totalBalance += balance;
            }

            if (showInfo) {
                simulationResults.add("Total Portfolio Balance: " + String.format("%.2f", totalBalance) + "\n");
            }
        }

        if (!showInfo) {
            simulationResults.addAll(showFinalBalance());
        }

        return simulationResults;
    }

    public List<String> showFinalBalance() {
        List<String> finalResults = new ArrayList<>();
        finalResults.add("\n--- Final Portfolio Summary ---");

        double totalBalance = 0;

        for (Stock stock : stocks) {
            double stockBalance = stock.getTotalShares() * stock.getStockPrice();
            totalBalance += stockBalance;
            finalResults.add(stock.getName() + ":");
            finalResults.add("  Current Price: " + String.format("%.2f", stock.getStockPrice()) + " " + stock.getCurrency());
            finalResults.add("  Total Shares: " + String.format("%.4f", (double) stock.getTotalShares()));
            finalResults.add("  Balance: " + String.format("%.2f", stockBalance) + " " + stock.getCurrency());
        }

        finalResults.add("\nTotal Portfolio Balance: " + String.format("%.2f", totalBalance));
        return finalResults;
    }

}
