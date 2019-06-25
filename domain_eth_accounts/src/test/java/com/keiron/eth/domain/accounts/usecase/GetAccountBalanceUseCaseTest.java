package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.repository.AccountRepository;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAccountBalanceUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    private GetAccountBalanceUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);

        classUnderTest = new GetAccountBalanceUseCase(accountRepository);
    }

    @Test
    public void givenAddressWhenBuildUseCaseThenReturnsAccountBalanceFromRepository() {
        // Given
        String address = "address";
        BigDecimal expectedValue = new BigDecimal(1234);
        when(accountRepository.getAccountBalance(address)).thenReturn(Single.just(expectedValue));

        // When
        TestObserver<BigDecimal> testObserver = classUnderTest.buildUseCase(address).test();

        // Then
        testObserver.assertComplete().assertNoErrors().assertValue(expectedValue);
    }
}