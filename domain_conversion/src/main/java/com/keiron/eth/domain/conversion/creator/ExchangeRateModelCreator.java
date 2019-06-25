package com.keiron.eth.domain.conversion.creator;

import com.keiron.eth.domain.conversion.model.ExchangeRate;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.inject.Inject;
import java.math.BigDecimal;

public class ExchangeRateModelCreator {

    @Inject
    public ExchangeRateModelCreator() {

    }

    public ExchangeRate create(Params params) {
        return new ExchangeRate(params.symbol, params.rate);
    }

    public static class Params {

        private final String symbol;
        private final BigDecimal rate;

        public Params(String symbol, BigDecimal rate) {

            this.symbol = symbol;
            this.rate = rate;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Params params = (Params) o;

            return new EqualsBuilder()
                    .append(symbol, params.symbol)
                    .append(rate, params.rate)
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(symbol)
                    .append(rate)
                    .toHashCode();
        }
    }
}
