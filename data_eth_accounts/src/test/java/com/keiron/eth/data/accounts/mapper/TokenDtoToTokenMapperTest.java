package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.TokenDto;
import com.keiron.eth.domain.accounts.model.Token;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TokenDtoToTokenMapperTest {

    private TokenDtoToTokenMapper classUnderTest;

    @Before
    public void setUp() throws Exception {
        classUnderTest = new TokenDtoToTokenMapper();
    }

    @Test
    public void givenSmartContractDtoWhenMapToDomainThenReturnsSmartContract() {
        // Given
        TokenDto tokenDto = mock(TokenDto.class);
        String expectedAddress = "address";
        when(tokenDto.getAddress()).thenReturn(expectedAddress);
        String expectedName = "Coolname";
        when(tokenDto.getName()).thenReturn(expectedName);
        String expectedSymbol = "XYZ";
        when(tokenDto.getSymbol()).thenReturn(expectedSymbol);
        // When
        Token token = classUnderTest.mapToDomain(tokenDto);

        // Then
        assertEquals(expectedAddress, token.getAddress());
        assertEquals(expectedName, token.getName());
        assertEquals(expectedSymbol, token.getSymbol());
    }
}