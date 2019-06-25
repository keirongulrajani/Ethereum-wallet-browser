package com.keiron.eth.smartcontracttest.screens.main.model;

public class MainUiModel {
    private String address;
    private final String ethBalance;
    private final String tokenBalance;

    public MainUiModel(String address, String ethBalance, String tokenBalance) {
        this.address = address;
        this.ethBalance = ethBalance;
        this.tokenBalance = tokenBalance;
    }

    public String getEthBalance() {
        return ethBalance;
    }

    public String getTokenBalance() {
        return tokenBalance;
    }

    public String getAddress() {
        return address;
    }
}