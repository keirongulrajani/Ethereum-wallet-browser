package com.keiron.eth.domain.conversion.usecase;

import com.keiron.eth.domain.conversion.creator.ExchangeRateModelCreator;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.repository.ConversionRepository;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetEthExchangeRateForSmartContractUseCaseTest {
    @Mock
    private ConversionRepository conversionRepository;
    @Mock
    private ExchangeRateModelCreator exchangeRateModelCreator;

    private GetEthExchangeRateForSmartContractUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new GetEthExchangeRateForSmartContractUseCase(conversionRepository, exchangeRateModelCreator);
    }

    @Test
    public void givenSmartContractSymbolWhenBuildUseCaseThenGetsRateFromRepository() {
        // Given
        String symbol = "XYZ";
        BigDecimal expectedRate = BigDecimal.TEN;
        when(conversionRepository.getConversionRateForEthPair(symbol)).thenReturn(Single.just(expectedRate));
        ExchangeRate expectedExchangeRate = mock(ExchangeRate.class);
        when(exchangeRateModelCreator.create(new ExchangeRateModelCreator.Params(symbol, expectedRate))).thenReturn(expectedExchangeRate);

        // When
        TestObserver<ExchangeRate> testObserver = classUnderTest.buildUseCase(symbol).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedExchangeRate);
    }
}