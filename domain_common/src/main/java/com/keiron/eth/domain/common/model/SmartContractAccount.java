package com.keiron.eth.domain.common.model;

import java.math.BigDecimal;

public class SmartContractAccount {

    private final String address;
    private String contractAddress;
    private final BigDecimal balance;
    private final String name;
    private final String symbol;
    private BigDecimal rate;

    public SmartContractAccount(String address, String contractAddress, BigDecimal balance, String name, String symbol, BigDecimal rate) {
        this.address = address;
        this.contractAddress = contractAddress;
        this.balance = balance;
        this.name = name;
        this.symbol = symbol;
        this.rate = rate;
    }

    public String getAddress() {
        return address;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
