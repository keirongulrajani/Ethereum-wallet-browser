package com.keiron.eth.domain.conversion.model;

import java.math.BigDecimal;

public class ExchangeRate {

    private final String symbol;
    private final BigDecimal rate;

    public ExchangeRate(String symbol, BigDecimal rate) {

        this.symbol = symbol;
        this.rate = rate;
    }

    public String getSymbol() {
        return symbol;
    }

    public BigDecimal getRate() {
        return rate;
    }
}
