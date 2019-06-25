package com.keiron.eth.domain.accounts.creator;

import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.TokenAccount;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class EthereumAccountModelCreator {

    @Inject
    public EthereumAccountModelCreator() {
    }

    public EthereumAccount create(EthereumAccountModelCreator.Params params) {
        return new EthereumAccount(params.address, params.balance, params.tokenTotalBalance, params.tokenAccounts);
    }

    public static class Params {
        private final String address;
        private final BigDecimal balance;
        private final BigDecimal tokenTotalBalance;
        private final List<TokenAccount> tokenAccounts;

        public Params(String address, BigDecimal balance, BigDecimal tokenTotalBalance, List<TokenAccount> tokenAccounts) {
            this.address = address;
            this.balance = balance;
            this.tokenTotalBalance = tokenTotalBalance;
            this.tokenAccounts = tokenAccounts;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return new EqualsBuilder()
                    .append(address, params.address)
                    .append(balance, params.balance)
                    .append(tokenTotalBalance, params.tokenTotalBalance)
                    .append(tokenAccounts, params.tokenAccounts)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(address)
                    .append(balance)
                    .append(tokenTotalBalance)
                    .append(tokenAccounts)
                    .toHashCode();
        }
    }
}
