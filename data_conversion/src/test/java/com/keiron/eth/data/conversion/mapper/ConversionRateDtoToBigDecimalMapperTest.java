package com.keiron.eth.data.conversion.mapper;

import com.keiron.eth.data.conversion.model.ConversionRateDto;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ConversionRateDtoToBigDecimalMapperTest {

    private ConversionRateDtoToBigDecimalMapper classUnderTest;

    @Before
    public void setUp() {
        classUnderTest = new ConversionRateDtoToBigDecimalMapper();
    }

    @Test
    public void givenConversionRateDtoWhenMapToDomainThenReturnsRateFromDto() {
        // Given
        ConversionRateDto conversionRateDto = mock(ConversionRateDto.class);
        BigDecimal expectedRate = BigDecimal.TEN;
        when(conversionRateDto.getRate()).thenReturn(expectedRate);

        // When
        BigDecimal returnedRate = classUnderTest.mapToDomain(conversionRateDto);

        // Then
        assertEquals(expectedRate, returnedRate);
    }
}