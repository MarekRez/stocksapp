package com.example.stocksapp.model;

import jakarta.persistence.*;

@Entity
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private StockSymbol name;
    private String currency;
    private double stockPrice;
    private double dividendYield; // e.g. 0.02 for 2% annual dividend yield
    private double volatility;    // volatility of the stock
    private double expectedReturn; // expected annual change (price + dividends)
    private int totalShares;      // number of shares owned

    public Stock(StockSymbol name, String currency, double stockPrice, double dividendYield, double volatility, double expectedReturn, int totalShares) {
        if (name == null || currency == null || stockPrice <= 0 || dividendYield < 0 || volatility < 0 || expectedReturn < 0 || totalShares < 0) {
            throw new IllegalArgumentException("Invalid input values for Stock creation");
        }
        this.name = name;
        this.currency = currency;
        this.stockPrice = stockPrice;
        this.dividendYield = dividendYield;
        this.volatility = volatility;
        this.expectedReturn = expectedReturn;
        this.totalShares = totalShares;
    }

    // Overloaded constructor - default totalShares = 0
    public Stock(StockSymbol name, String currency, double stockPrice, double dividendYield, double volatility, double expectedReturn) {
        this(name, currency, stockPrice, dividendYield, volatility, expectedReturn, 0); // Default totalShares is 0
    }

    public Stock() {
    }

    public StockSymbol getName() {
        return name;
    }

    public void setName(StockSymbol name) {
        this.name = name;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
    }

    public double getDividendYield() {
        return dividendYield;
    }

    public void setDividendYield(double dividendYield) {
        this.dividendYield = dividendYield;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

    public double getExpectedReturn() {
        return expectedReturn;
    }

    public void setExpectedReturn(double expectedReturn) {
        this.expectedReturn = expectedReturn;
    }

    public int getTotalShares() {
        return totalShares;
    }

    public void setTotalShares(int totalShares) {
        this.totalShares = totalShares;
    }
}

