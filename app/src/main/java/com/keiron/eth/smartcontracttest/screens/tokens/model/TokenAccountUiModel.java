package com.keiron.eth.smartcontracttest.screens.tokens.model;

public class TokenAccountUiModel {

    private final String balance;
    private final String balanceInEth;
    private String title;

    public TokenAccountUiModel(String title, String balance, String balanceInEth) {
        this.title = title;
        this.balance = balance;
        this.balanceInEth = balanceInEth;
    }

    public String getTitle() {
        return title;
    }

    public String getBalance() {
        return balance;
    }

    public String getBalanceInEth() {
        return balanceInEth;
    }
}
