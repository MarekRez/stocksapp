package com.example.stocksapp.model.dto;

public class ClientRequest {

    private String name;
    private String email;
    private double bankAccountBalance;
    private double investmentAccountBalance;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBankAccountBalance() {
        return bankAccountBalance;
    }

    public void setBankAccountBalance(double bankAccountBalance) {
        this.bankAccountBalance = bankAccountBalance;
    }

    public double getInvestmentAccountBalance() {
        return investmentAccountBalance;
    }

    public void setInvestmentAccountBalance(double investmentAccountBalance) {
        this.investmentAccountBalance = investmentAccountBalance;
    }
}