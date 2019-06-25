package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.domain.accounts.repository.AccountRepository;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class GetAvailableSmartContractsUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    private GetAvailableSmartContractsUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);

        classUnderTest = new GetAvailableSmartContractsUseCase(accountRepository);
    }

    @Test
    public void givenListOfSmartContractsWhenBuildUseCaseThenReturnsList() {
        // Given
        List<SmartContract> smartContractList = Collections.singletonList(mock(SmartContract.class));
        when(accountRepository.getListOfSupportedContracts()).thenReturn(Single.just(smartContractList));

        // When
        TestObserver<List<SmartContract>> testObserver = classUnderTest.buildUseCase().test();

        // Then
        testObserver.assertComplete().assertNoErrors().assertValue(smartContractList);
    }
}