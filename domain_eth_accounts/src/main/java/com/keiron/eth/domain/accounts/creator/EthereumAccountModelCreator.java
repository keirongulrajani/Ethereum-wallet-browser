package com.keiron.eth.domain.accounts.creator;

import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.SmartContractAccount;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.math.BigDecimal;
import java.util.List;

import javax.inject.Inject;

public class EthereumAccountModelCreator {

    @Inject
    public EthereumAccountModelCreator() {
    }

    public EthereumAccount create(EthereumAccountModelCreator.Params params) {
        return new EthereumAccount(params.address, params.balance, params.smartContractTotalBalance, params.smartContractAccounts);
    }

    public static class Params {
        private final String address;
        private final BigDecimal balance;
        private final BigDecimal smartContractTotalBalance;
        private final List<SmartContractAccount> smartContractAccounts;

        public Params(String address, BigDecimal balance, BigDecimal smartContractTotalBalance, List<SmartContractAccount> smartContractAccounts) {
            this.address = address;
            this.balance = balance;
            this.smartContractTotalBalance = smartContractTotalBalance;
            this.smartContractAccounts = smartContractAccounts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return new EqualsBuilder()
                    .append(address, params.address)
                    .append(balance, params.balance)
                    .append(smartContractTotalBalance, params.smartContractTotalBalance)
                    .append(smartContractAccounts, params.smartContractAccounts)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(address)
                    .append(balance)
                    .append(smartContractTotalBalance)
                    .append(smartContractAccounts)
                    .toHashCode();
        }
    }
}
