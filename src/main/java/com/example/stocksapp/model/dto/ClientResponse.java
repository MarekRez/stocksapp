package com.example.stocksapp.model.dto;

public class ClientResponse {
    private String name;
    private String email;
    private String iban;
    private double bankAccountBalance;
    private double investmentAccountBalance;

    public ClientResponse(String name, String email, String iban, double bankAccountBalance, double investmentAccountBalance) {
        this.name = name;
        this.email = email;
        this.iban = iban;
        this.bankAccountBalance = bankAccountBalance;
        this.investmentAccountBalance = investmentAccountBalance;
    }

    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getIban() { return iban; }
    public double getBankAccountBalance() { return bankAccountBalance; }
    public double getInvestmentAccountBalance() { return investmentAccountBalance; }
}
