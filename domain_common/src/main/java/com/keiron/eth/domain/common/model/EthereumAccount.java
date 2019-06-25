package com.keiron.eth.domain.common.model;

import java.math.BigDecimal;
import java.util.List;

public class EthereumAccount {
    private final String address;
    private final BigDecimal balance;
    private BigDecimal smartContractBalance;
    private List<SmartContractAccount> smartContractAccounts;

    public EthereumAccount(String address, BigDecimal mainBalance, BigDecimal smartContractBalance, List<SmartContractAccount> smartContractAccounts) {
        this.address = address;
        this.balance = mainBalance;
        this.smartContractBalance = smartContractBalance;
        this.smartContractAccounts = smartContractAccounts;
    }

    public String getAddress() {
        return address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BigDecimal getSmartContractBalance() {
        return smartContractBalance;
    }

    public List<SmartContractAccount> getSmartContractAccounts() {
        return smartContractAccounts;
    }
}
