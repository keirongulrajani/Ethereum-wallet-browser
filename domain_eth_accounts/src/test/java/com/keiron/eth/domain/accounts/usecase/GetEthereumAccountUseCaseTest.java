package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.EthereumAccountModelCreator;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.SmartContractAccount;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetEthereumAccountUseCaseTest {

    @Mock
    private GetAccountBalanceUseCase getAccountBalanceUseCase;
    @Mock
    private EthereumAccountModelCreator ethereumAccountModelCreator;
    @Mock
    private GetSmartContractAccountsForEthereumAccountUseCase getSmartContractAccountsForEthereumAccountUseCase;

    private GetEthereumAccountUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new GetEthereumAccountUseCase(getAccountBalanceUseCase,
                getSmartContractAccountsForEthereumAccountUseCase,
                ethereumAccountModelCreator);
    }

    @Test
    public void givenAddressWhenBuildUseCaseThenFetchesAccountDataAndReturns() {
        // Given
        String address = "address";
        BigDecimal ethBalance = new BigDecimal(1234);
        when(getAccountBalanceUseCase.buildUseCase(address)).thenReturn(Single.just(ethBalance));

        SmartContractAccount smartContractAccount = mock(SmartContractAccount.class);
        when(smartContractAccount.getBalance()).thenReturn(BigDecimal.ONE);
        when(smartContractAccount.getRate()).thenReturn(BigDecimal.ONE);
        List<SmartContractAccount> smartContractAccounts = Collections.singletonList(smartContractAccount);

        when(getSmartContractAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(smartContractAccounts));

        EthereumAccount expectedEthereumAccount = mock(EthereumAccount.class);

        when(ethereumAccountModelCreator.create(new EthereumAccountModelCreator.Params(address, ethBalance, BigDecimal.ONE, smartContractAccounts))).thenReturn(expectedEthereumAccount);
        // When
        TestObserver<EthereumAccount> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedEthereumAccount);
    }

    @Test
    public void givenAccountDataFetchedWhenBuildUseCaseThenCalculatesBalanceOfTokensInEth() {
        String address = "address";
        BigDecimal ethBalance = BigDecimal.ONE;
        when(getAccountBalanceUseCase.buildUseCase(address)).thenReturn(Single.just(ethBalance));

        SmartContractAccount smartContractAccount = mock(SmartContractAccount.class);
        BigDecimal originalBalance = new BigDecimal(123);
        when(smartContractAccount.getBalance()).thenReturn(originalBalance);
        BigDecimal rate = BigDecimal.TEN;
        when(smartContractAccount.getRate()).thenReturn(rate);

        SmartContractAccount smartContractAccount2 = mock(SmartContractAccount.class);
        BigDecimal originalBalance2 = new BigDecimal(456);
        when(smartContractAccount2.getBalance()).thenReturn(originalBalance2);
        BigDecimal rate2 = BigDecimal.TEN;
        when(smartContractAccount2.getRate()).thenReturn(rate2);

        BigDecimal expectedBalance = originalBalance.multiply(rate).add(originalBalance2.multiply(rate2));
        List<SmartContractAccount> smartContractAccounts = Arrays.asList(smartContractAccount, smartContractAccount2);

        when(getSmartContractAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(smartContractAccounts));

        when(ethereumAccountModelCreator.create(new EthereumAccountModelCreator.Params(address, ethBalance, expectedBalance, smartContractAccounts))).thenReturn(mock(EthereumAccount.class));
        // When
        TestObserver<EthereumAccount> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors();

        verify(ethereumAccountModelCreator).create(new EthereumAccountModelCreator.Params(address, ethBalance, expectedBalance, smartContractAccounts));
    }
}