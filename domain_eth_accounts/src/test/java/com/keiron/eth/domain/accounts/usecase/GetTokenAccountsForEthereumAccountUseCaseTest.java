package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.TokenAccountModelCreator;
import com.keiron.eth.domain.accounts.model.Token;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.usecase.GetEthExchangeRateForTokenUseCase;
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

public class GetTokenAccountsForEthereumAccountUseCaseTest {

    @Mock
    private GetAvailableTokensUseCase getAvailableTokensUseCase;
    @Mock
    private GetTokenAccountBalanceUseCase getTokenAccountBalanceUseCase;
    @Mock
    private GetEthExchangeRateForTokenUseCase getEthExchangeRateForTokenUseCase;
    @Mock
    private TokenAccountModelCreator tokenAccountModelCreator;

    private GetTokenAccountsForEthereumAccountUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new GetTokenAccountsForEthereumAccountUseCase(getAvailableTokensUseCase,
                getTokenAccountBalanceUseCase,
                getEthExchangeRateForTokenUseCase,
                tokenAccountModelCreator);
    }

    @Test
    public void givenEmptyListOfAvailableSmartContractsWhenBuildUseCaseThenReturnsEmptyList() {
        // Given
        String address = "address";
        when(getAvailableTokensUseCase.buildUseCase()).thenReturn(Single.just(Collections.emptyList()));

        // When
        TestObserver<List<TokenAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.emptyList());
    }

    @Test
    public void givenListOfAvailableSmartContractsWhenBuildUseCaseThenFetchesBalancesAndReturnsListOfSmartContractAccounts() {
        // Given
        String address = "address";
        Token token = mock(Token.class);
        String contractAddress = "contract address";
        when(token.getAddress()).thenReturn(contractAddress);
        String symbol = "symbol";
        when(token.getSymbol()).thenReturn(symbol);
        String name = "name";
        when(token.getName()).thenReturn(name);
        when(getAvailableTokensUseCase.buildUseCase()).thenReturn(Single.just(Collections.singletonList(token)));
        BigDecimal balance = BigDecimal.TEN;
        when(getTokenAccountBalanceUseCase.buildUseCase(new GetTokenAccountBalanceUseCase.Params(contractAddress, address))).thenReturn(Single.just(balance));
        ExchangeRate exchangeRate = mock(ExchangeRate.class);
        BigDecimal rate = BigDecimal.ONE;
        when(exchangeRate.getRate()).thenReturn(rate);
        when(getEthExchangeRateForTokenUseCase.buildUseCase(symbol)).thenReturn(Single.just(exchangeRate));
        TokenAccount expectedAccount = mock(TokenAccount.class);
        when(tokenAccountModelCreator.create(new TokenAccountModelCreator.Params(address,
                contractAddress,
                balance,
                name,
                symbol,
                rate))).thenReturn(expectedAccount);
        // When
        TestObserver<List<TokenAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.singletonList(expectedAccount));
    }

    @Test
    public void givenListOfAvailableSmartContractsAndFetchesZeroBalanceWhenBuildUseCaseThenReturnsEmptyList() {
        // Given
        String address = "address";
        Token token = mock(Token.class);
        String contractAddress = "contract address";
        when(token.getAddress()).thenReturn(contractAddress);
        String symbol = "symbol";
        when(token.getSymbol()).thenReturn(symbol);
        String name = "name";
        when(token.getName()).thenReturn(name);
        when(getAvailableTokensUseCase.buildUseCase()).thenReturn(Single.just(Collections.singletonList(token)));
        BigDecimal balance = BigDecimal.ZERO;
        when(getTokenAccountBalanceUseCase.buildUseCase(new GetTokenAccountBalanceUseCase.Params(contractAddress, address))).thenReturn(Single.just(balance));

        // When
        TestObserver<List<TokenAccount>> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(Collections.emptyList());

        verify(getEthExchangeRateForTokenUseCase, never()).buildUseCase(any());
        verify(tokenAccountModelCreator, never()).create(any());
    }
}