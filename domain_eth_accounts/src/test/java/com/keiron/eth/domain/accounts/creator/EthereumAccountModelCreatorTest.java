package com.keiron.eth.domain.accounts.creator;

import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.SmartContractAccount;

import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class EthereumAccountModelCreatorTest {

    private EthereumAccountModelCreator classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new EthereumAccountModelCreator();
    }

    @Test
    public void givenParamsWhenCreateThenREturnsEthereumAccountWithCorrectData() {
        // Given
        String address = "address";
        BigDecimal balance = new BigDecimal(123456);
        BigDecimal smartContractBalance = new BigDecimal(654321);
        List<SmartContractAccount> smartContractAccounts = Collections.singletonList(mock(SmartContractAccount.class));
        // When
        EthereumAccount ethereumAccount = classUnderTest.create(new EthereumAccountModelCreator.Params(address, balance, smartContractBalance,
                smartContractAccounts));
        // Then
        assertEquals(address, ethereumAccount.getAddress());
        assertEquals(balance, ethereumAccount.getBalance());
        assertEquals(smartContractBalance, ethereumAccount.getSmartContractBalance());
        assertEquals(smartContractAccounts, ethereumAccount.getSmartContractAccounts());
    }
}