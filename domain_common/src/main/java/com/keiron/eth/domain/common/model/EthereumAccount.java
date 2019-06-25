package com.keiron.eth.domain.common.model;

import java.math.BigDecimal;
import java.util.List;

public class EthereumAccount {
    private final String address;
    private final BigDecimal balance;
    private BigDecimal tokenBalance;
    private List<TokenAccount> tokenAccounts;

    public EthereumAccount(String address, BigDecimal mainBalance, BigDecimal tokenBalance, List<TokenAccount> tokenAccounts) {
        this.address = address;
        this.balance = mainBalance;
        this.tokenBalance = tokenBalance;
        this.tokenAccounts = tokenAccounts;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getTokenBalance() {
        return tokenBalance;
    }

    public List<TokenAccount> getTokenAccounts() {
        return tokenAccounts;
    }
}
