package com.keiron.eth.data.conversion.repository;

import com.keiron.eth.data.conversion.datasource.ConversionDataSource;
import com.keiron.eth.data.conversion.mapper.ConversionRateDtoToBigDecimalMapper;
import com.keiron.eth.data.conversion.model.ConversionRateDto;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConversionRepositoryImplTest {

    @Mock
    private ConversionDataSource conversionDataSource;
    @Mock
    private ConversionRateDtoToBigDecimalMapper conversionRateDtoToBigDecimalMapper;

    private ConversionRepositoryImpl classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new ConversionRepositoryImpl(conversionDataSource, conversionRateDtoToBigDecimalMapper);
    }

    @Test
    public void givenSmartContractSymbolWhenGetConversionRateForEthPairThenFetchesRateAndMapsToDomain() {
        // Given
        String symbol = "XYZ";
        ConversionRateDto conversionRateDto = mock(ConversionRateDto.class);
        when(conversionDataSource.getConversionRateForEthPair(symbol)).thenReturn(Single.just(conversionRateDto));
        BigDecimal expectedRate = BigDecimal.TEN;
        when(conversionRateDtoToBigDecimalMapper.mapToDomain(conversionRateDto)).thenReturn(expectedRate);

        // When
        TestObserver<BigDecimal> testObserver = classUnderTest.getConversionRateForEthPair(symbol).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedRate);
    }
}