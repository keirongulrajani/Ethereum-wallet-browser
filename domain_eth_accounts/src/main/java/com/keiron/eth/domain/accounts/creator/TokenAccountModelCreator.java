package com.keiron.eth.domain.accounts.creator;

import com.keiron.eth.domain.common.model.TokenAccount;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;
import java.math.BigDecimal;

public class TokenAccountModelCreator {

    @Inject
    public TokenAccountModelCreator() {
    }

    public TokenAccount create(TokenAccountModelCreator.Params params) {
        return new TokenAccount(params.address, params.contractAddress, params.balance, params.name, params.symbol, params.rate);
    }

    public static class Params {

        private final String address;
        private final String contractAddress;
        private final BigDecimal balance;
        private final String name;
        private final String symbol;
        private BigDecimal rate;

        public Params(String address, String contractAddress, BigDecimal balance, String name, String symbol, BigDecimal rate) {
            this.address = address;
            this.contractAddress = contractAddress;
            this.balance = balance;
            this.name = name;
            this.symbol = symbol;
            this.rate = rate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return new EqualsBuilder()
                    .append(address, params.address)
                    .append(contractAddress, params.contractAddress)
                    .append(balance, params.balance)
                    .append(name, params.name)
                    .append(symbol, params.symbol)
                    .append(rate, params.rate)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(address)
                    .append(contractAddress)
                    .append(balance)
                    .append(name)
                    .append(symbol)
                    .append(rate)
                    .toHashCode();
        }
    }
}
