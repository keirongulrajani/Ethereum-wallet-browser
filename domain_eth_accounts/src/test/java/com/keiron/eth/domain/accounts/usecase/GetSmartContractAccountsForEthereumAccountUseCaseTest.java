package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.SmartContractAccountModelCreator;
import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.domain.common.model.SmartContractAccount;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.usecase.GetEthExchangeRateForSmartContractUseCase;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetSmartContractAccountsForEthereumAccountUseCaseTest {

    @Mock
    private GetAvailableSmartContractsUseCase getAvailableSmartContractsUseCase;
    @Mock
    private GetSmartContractAccountBalanceUseCase getSmartContractAccountBalanceUseCase;
    @Mock
    private GetEthExchangeRateForSmartContractUseCase getEthExchangeRateForSmartContractUseCase;
    @Mock
    private SmartContractAccountModelCreator smartContractAccountModelCreator;

    private GetSmartContractAccountsForEthereumAccountUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new GetSmartContractAccountsForEthereumAccountUseCase(getAvailableSmartContractsUseCase,
                getSmartContractAccountBalanceUseCase,
                getEthExchangeRateForSmartContractUseCase,
                smartContractAccountModelCreator);
    }

    @Test
    public void givenEmptyListOfAvailableSmartContractsWhenBuildUseCaseThenReturnsEmptyList() {
        // Given
        String address = "address";
        when(getAvailableSmartContractsUseCase.buildUseCase()).thenReturn(Single.just(Collections.emptyList()));

        // When
        TestObserver<List<SmartContractAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.emptyList());
    }

    @Test
    public void givenListOfAvailableSmartContractsWhenBuildUseCaseThenFetchesBalancesAndReturnsListOfSmartContractAccounts() {
        // Given
        String address = "address";
        SmartContract smartContract = mock(SmartContract.class);
        String contractAddress = "contract address";
        when(smartContract.getAddress()).thenReturn(contractAddress);
        String symbol = "symbol";
        when(smartContract.getSymbol()).thenReturn(symbol);
        String name = "name";
        when(smartContract.getName()).thenReturn(name);
        when(getAvailableSmartContractsUseCase.buildUseCase()).thenReturn(Single.just(Collections.singletonList(smartContract)));
        BigDecimal balance = BigDecimal.TEN;
        when(getSmartContractAccountBalanceUseCase.buildUseCase(new GetSmartContractAccountBalanceUseCase.Params(contractAddress, address))).thenReturn(Single.just(balance));
        ExchangeRate exchangeRate = mock(ExchangeRate.class);
        BigDecimal rate = BigDecimal.ONE;
        when(exchangeRate.getRate()).thenReturn(rate);
        when(getEthExchangeRateForSmartContractUseCase.buildUseCase(symbol)).thenReturn(Single.just(exchangeRate));
        SmartContractAccount expectedAccount = mock(SmartContractAccount.class);
        when(smartContractAccountModelCreator.create(new SmartContractAccountModelCreator.Params(address,
                contractAddress,
                balance,
                name,
                symbol,
                rate))).thenReturn(expectedAccount);
        // When
        TestObserver<List<SmartContractAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.singletonList(expectedAccount));
    }

    @Test
    public void givenListOfAvailableSmartContractsAndFetchesZeroBalanceWhenBuildUseCaseThenReturnsEmptyList() {
        // Given
        String address = "address";
        SmartContract smartContract = mock(SmartContract.class);
        String contractAddress = "contract address";
        when(smartContract.getAddress()).thenReturn(contractAddress);
        String symbol = "symbol";
        when(smartContract.getSymbol()).thenReturn(symbol);
        String name = "name";
        when(smartContract.getName()).thenReturn(name);
        when(getAvailableSmartContractsUseCase.buildUseCase()).thenReturn(Single.just(Collections.singletonList(smartContract)));
        BigDecimal balance = BigDecimal.ZERO;
        when(getSmartContractAccountBalanceUseCase.buildUseCase(new GetSmartContractAccountBalanceUseCase.Params(contractAddress, address))).thenReturn(Single.just(balance));

        // When
        TestObserver<List<SmartContractAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.emptyList());

        verify(getEthExchangeRateForSmartContractUseCase, never()).buildUseCase(any());
        verify(smartContractAccountModelCreator, never()).create(any());
    }
}