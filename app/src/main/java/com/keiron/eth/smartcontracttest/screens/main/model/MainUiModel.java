package com.keiron.eth.smartcontracttest.screens.main.model;

import java.math.BigDecimal;

public class MainUiModel {
    private String address;
    private final BigDecimal ethBalance;
    private final BigDecimal tokenBalance;

    public MainUiModel(String address, BigDecimal ethBalance, BigDecimal tokenBalance) {
        this.address = address;
        this.ethBalance = ethBalance;
        this.tokenBalance = tokenBalance;
    }

    public BigDecimal getEthBalance() {
        return ethBalance;
    }

    public BigDecimal getTokenBalance() {
        return tokenBalance;
    }

    public String getAddress() {
        return address;
    }
}