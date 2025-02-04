package com.example.stocksapp.controller;

import com.example.stocksapp.service.PortfolioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PortfolioController {

    @Autowired
    private PortfolioService portfolioService;

    @PostMapping("/portfolio")

    @SuppressWarnings("unchecked")
    public ResponseEntity<?> simulatePortfolio(@RequestBody Map<String, Object> request) {
        try {

            Object stocksObj = request.get("stocks");
            List<Map<String, Object>> stocks;

            if (stocksObj instanceof List<?>) {
                stocks = (List<Map<String, Object>>) stocksObj;
            } else {
                return ResponseEntity.badRequest().body(Map.of("error", "'stocks' must be a list of objects"));
            }

            int months = ((Number) request.get("months")).intValue();
            boolean showInfo = request.getOrDefault("showInfo", true) instanceof Boolean && (Boolean) request.get("showInfo");
//            Object showInfoObj = request.get("showInfo");
//            boolean showInfo = (showInfoObj instanceof Boolean) ? (Boolean) showInfoObj : true;

            if (months <= 0) {
                return ResponseEntity.badRequest().body(Map.of("error", "Invalid input: stocks should be an array and months should be a positive number"));
            }

            List<String> simulationResults = portfolioService.simulatePortfolio(stocks, months, showInfo);

            return ResponseEntity.ok(Map.of(
                    "message", "Simulated portfolio over " + months + " months",
                    "details", simulationResults
            ));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
