package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.EthereumAccountModelCreator;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.SmartContractAccount;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
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

        List<SmartContractAccount> smartContractAccounts = Collections.singletonList(mock(SmartContractAccount.class));
        when(getSmartContractAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(smartContractAccounts));

        EthereumAccount expectedEthereumAccount = mock(EthereumAccount.class);

        when(ethereumAccountModelCreator.create(new EthereumAccountModelCreator.Params(address, ethBalance, smartContractAccounts))).thenReturn(expectedEthereumAccount);
        // When
        TestObserver<EthereumAccount> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedEthereumAccount);
    }
}