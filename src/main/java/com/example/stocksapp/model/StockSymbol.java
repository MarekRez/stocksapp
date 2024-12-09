package com.example.stocksapp.model;

public enum StockSymbol {
    AAPL("AAPL"),   // Apple
    GOOGL("GOOGL"), // Alphabet (Google)
    MSFT("MSFT"),   // Microsoft
    TSLA("TSLA"),   // Tesla
    AMZN("AMZN"),   // Amazon
    META("META"),   // Meta Platforms (Facebook)
    NFLX("NFLX"),   // Netflix
    NVDA("NVDA"),   // NVIDIA
    BABA("BABA"),   // Alibaba
    JPM("JPM"),     // JPMorgan Chase
    V("V"),         // Visa
    WMT("WMT"),     // Walmart
    DIS("DIS"),     // Disney
    PG("PG"),       // Procter & Gamble
    KO("KO"),       // Coca-Cola
    MCD("MCD"),     // McDonald's
    NKE("NKE");     // Nike

    private final String symbol;

    // Constructor
    StockSymbol(String symbol) {
        this.symbol = symbol;
    }

    // Getter method for symbol
    public String getSymbol() {
        return symbol;
    }
}