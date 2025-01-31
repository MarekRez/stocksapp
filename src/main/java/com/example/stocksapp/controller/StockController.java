package com.example.stocksapp.controller;

import com.example.stocksapp.model.Stock;
import com.example.stocksapp.model.StockSymbol;
import com.example.stocksapp.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping("/stocks/buy")
    public ResponseEntity<?> buyStock(@RequestBody Map<String, Object> request) {
        try {
            String email = (String) request.get("email");
            StockSymbol stockSymbol = StockSymbol.valueOf((String) request.get("stockSymbol")); // Convert String to Enum
            String currency = (String) request.get("currency");
            double stockPrice = ((Number) request.get("stockPrice")).doubleValue();
            double dividendYield = ((Number) request.get("dividendYield")).doubleValue();
            double volatility = ((Number) request.get("volatility")).doubleValue();
            double expectedReturn = ((Number) request.get("expectedReturn")).doubleValue();
            int quantity = ((Number) request.get("quantity")).intValue();

            Stock stock = stockService.buyStock(email, stockSymbol, currency, stockPrice, dividendYield, volatility, expectedReturn, quantity);
            return ResponseEntity.ok(stock);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/stocks/sell")
    public ResponseEntity<?> sellStock(@RequestBody Map<String, Object> request) {
        try {
            String email = (String) request.get("email");
            StockSymbol stockSymbol = StockSymbol.valueOf((String) request.get("stockSymbol"));
            int quantity = ((Number) request.get("quantity")).intValue();

            stockService.sellStock(email, stockSymbol, quantity);
            return ResponseEntity.ok(Map.of("message", "Stock sold successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/clients/stocks")
    public ResponseEntity<List<Map<String, Object>>> getAllStocks() {
        List<Map<String, Object>> stocks = stockService.getAllStocks();
        return ResponseEntity.ok(stocks);
    }


    @GetMapping("/stocks/{email}")
    public ResponseEntity<List<Stock>> getStocksByPerson(@PathVariable String email) {
        return ResponseEntity.ok(stockService.getStocksByPerson(email));
    }

}
