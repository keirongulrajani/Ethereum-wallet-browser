package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AccountBalanceDtoToBigDecimalMapperTest {

    private AccountBalanceDtoToBigDecimalMapper classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new AccountBalanceDtoToBigDecimalMapper();
    }

    @Test
    public void givenAccountBalanceDtoWhenMapToDomainThenReturnBalanceDividedCorrectly() {
        // Given
        AccountBalanceDto accountBalanceDto = mock(AccountBalanceDto.class);
        BigDecimal accountBalance = new BigDecimal(3053308661514738765L);
        when(accountBalanceDto.getResult()).thenReturn(accountBalance);

        BigDecimal expected = accountBalance.movePointLeft(18);
        // When
        BigDecimal result = classUnderTest.mapToDomain(accountBalanceDto);

        // Then
        assertEquals(expected, result);
    }
}