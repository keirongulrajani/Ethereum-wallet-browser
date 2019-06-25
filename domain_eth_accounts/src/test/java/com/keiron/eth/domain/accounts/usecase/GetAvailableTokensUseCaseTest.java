package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.model.Token;
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

public class GetAvailableTokensUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    private GetAvailableTokensUseCase classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);

        classUnderTest = new GetAvailableTokensUseCase(accountRepository);
    }

    @Test
    public void givenListOfSmartContractsWhenBuildUseCaseThenReturnsList() {
        // Given
        List<Token> tokenList = Collections.singletonList(mock(Token.class));
        when(accountRepository.getListOfSupportedTokens()).thenReturn(Single.just(tokenList));

        // When
        TestObserver<List<Token>> testObserver = classUnderTest.buildUseCase().test();

        // Then
        testObserver.assertComplete().assertNoErrors().assertValue(tokenList);
    }
}