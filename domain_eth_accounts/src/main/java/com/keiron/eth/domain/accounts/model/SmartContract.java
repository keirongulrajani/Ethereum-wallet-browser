package com.keiron.eth.domain.accounts.model;

public class SmartContract {

    private final String address;
    private String name;
    private final String symbol;

    public SmartContract(String address, String name, String symbol) {
        this.address = address;
        this.name = name;
        this.symbol = symbol;
    }

    public String getAddress() {
        return address;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }
}
