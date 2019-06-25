package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.SmartContractDto;
import com.keiron.eth.domain.accounts.model.SmartContract;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class SmartContractDtoToSmartContractMapperTest {

    private SmartContractDtoToSmartContractMapper classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new SmartContractDtoToSmartContractMapper();
    }

    @Test
    public void givenSmartContractDtoWhenMapToDomainThenReturnsSmartContract() {
        // Given
        SmartContractDto smartContractDto = mock(SmartContractDto.class);
        String expectedAddress = "address";
        when(smartContractDto.getAddress()).thenReturn(expectedAddress);
        String expectedName = "Coolname";
        when(smartContractDto.getName()).thenReturn(expectedName);
        String expectedSymbol = "XYZ";
        when(smartContractDto.getSymbol()).thenReturn(expectedSymbol);
        // When
        SmartContract smartContract = classUnderTest.mapToDomain(smartContractDto);

        // Then
        assertEquals(expectedAddress, smartContract.getAddress());
        assertEquals(expectedName, smartContract.getName());
        assertEquals(expectedSymbol, smartContract.getSymbol());
    }
}