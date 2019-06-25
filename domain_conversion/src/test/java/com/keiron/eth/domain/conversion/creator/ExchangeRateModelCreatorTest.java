package com.keiron.eth.domain.conversion.creator;

import com.keiron.eth.domain.conversion.model.ExchangeRate;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class ExchangeRateModelCreatorTest {

    private ExchangeRateModelCreator classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ExchangeRateModelCreator();
    }

    @Test
    public void givenParamsWithSymbolAndRateWhenCreateThenCreatesExchangeRateModel() {
        // Given
        String symbol = "xyz";
        BigDecimal rate = BigDecimal.TEN;

        // When
        ExchangeRate exchangeRate = classUnderTest.create(new ExchangeRateModelCreator.Params(symbol, rate));

        // Then
        assertEquals(rate, exchangeRate.getRate());
        assertEquals(symbol, exchangeRate.getSymbol());
    }
}