package com.keiron.eth.domain.common.model;

import java.math.BigDecimal;
import java.util.List;

public class EthereumAccount {
    private final String address;
    private final BigDecimal balance;
    private List<SmartContractAccount> smartContractAccounts;

    public EthereumAccount(String address, BigDecimal balance, List<SmartContractAccount> smartContractAccounts) {
        this.address = address;
        this.balance = balance;
        this.smartContractAccounts = smartContractAccounts;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<SmartContractAccount> getSmartContractAccounts() {
        return smartContractAccounts;
    }
}
