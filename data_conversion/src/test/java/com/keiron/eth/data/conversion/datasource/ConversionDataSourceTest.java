package com.keiron.eth.data.conversion.datasource;

import com.keiron.eth.data.conversion.client.ConversionClient;
import com.keiron.eth.data.conversion.model.ConversionRateDto;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ConversionDataSourceTest {

    @Mock
    private ConversionClient conversionClient;

    private ConversionDataSource classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new ConversionDataSource(conversionClient);
    }

    @Test
    public void givenSmartContractSymbolWhenGetConversionRateForEthPairThenReturnsRateDtoFromClient() {
        // Given
        String symbol = "XYZ";
        ConversionRateDto conversionRateDto = mock(ConversionRateDto.class);
        when(conversionClient.getConversionRateForEthPair(symbol)).thenReturn(Single.just(conversionRateDto));

        // When
        TestObserver<ConversionRateDto> testObserver = classUnderTest.getConversionRateForEthPair(symbol).test();

        // Then
        verify(conversionClient).getConversionRateForEthPair(symbol);
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(conversionRateDto);
    }

}