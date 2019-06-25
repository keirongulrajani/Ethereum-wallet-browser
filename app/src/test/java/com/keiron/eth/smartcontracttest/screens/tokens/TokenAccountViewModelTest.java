package com.keiron.eth.smartcontracttest.screens.tokens;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.keiron.eth.domain.accounts.usecase.GetTokenAccountsForEthereumAccountUseCase;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import com.keiron.eth.smartcontracttest.screens.tokens.mapper.TokenAccountToTokenAccountUiModelMapper;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountViewState;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class TokenAccountViewModelTest {

    @Rule
    public TestRule testRule = new InstantTaskExecutorRule();

    @Mock
    private GetTokenAccountsForEthereumAccountUseCase getTokenAccountsForEthereumAccountUseCase;
    @Mock
    private TokenAccountToTokenAccountUiModelMapper mapper;
    @Mock
    private SchedulersProvider schedulersProvider;
    @Mock
    private Observer<TokenAccountViewState> tokenAccountViewStateObserver;

    private TokenAccountViewModel classUnderTest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);

        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.mainThread()).thenReturn(Schedulers.trampoline());

        classUnderTest = new TokenAccountViewModel(getTokenAccountsForEthereumAccountUseCase, mapper, schedulersProvider);

        classUnderTest.tokenAccountsViewState.observeForever(tokenAccountViewStateObserver);
    }

    @Test
    public void givenAddressWhenOnEthAddressSubmittedThenFetchesEthAccountAndMapsToPresentation() {
        // Given
        String address = "address";
        TokenAccount tokenAccount = mock(TokenAccount.class);
        List<TokenAccount> tokenAccounts = Collections.singletonList(tokenAccount);
        when(getTokenAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(tokenAccounts));
        TokenAccountUiModel tokenAccountUiModel = mock(TokenAccountUiModel.class);
        when(mapper.mapToPresentation(tokenAccounts)).thenReturn(Collections.singletonList(tokenAccountUiModel));

        // When
        classUnderTest.onNavigateToView(address);

        // Then
        verify(getTokenAccountsForEthereumAccountUseCase).buildUseCase(address);
        verify(mapper).mapToPresentation(tokenAccounts);
    }

    @Test
    public void givenAddressWhenOnEthAddressSubmittedThenObserveLoadingAndSuccessViewStates() {
        // Given
        String address = "address";
        TokenAccount tokenAccount = mock(TokenAccount.class);
        List<TokenAccount> tokenAccounts = Collections.singletonList(tokenAccount);
        when(getTokenAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(tokenAccounts));
        TokenAccountUiModel tokenAccountUiModel = mock(TokenAccountUiModel.class);
        List<TokenAccountUiModel> expectedUiModelList = Collections.singletonList(tokenAccountUiModel);
        when(mapper.mapToPresentation(tokenAccounts)).thenReturn(expectedUiModelList);

        // When
        classUnderTest.onNavigateToView(address);

        // Then
        ArgumentCaptor<TokenAccountViewState> argumentCaptor = ArgumentCaptor.forClass(TokenAccountViewState.class);
        verify(tokenAccountViewStateObserver, times(2)).onChanged(argumentCaptor.capture());

        TokenAccountViewState viewStateLoading = argumentCaptor.getAllValues().get(0);
        assertTrue(viewStateLoading.isLoading());
        assertEquals(TokenAccountViewState.Error.NONE, viewStateLoading.getError());
        assertTrue(viewStateLoading.getUiModels().isEmpty());

        TokenAccountViewState viewStateSuccess = argumentCaptor.getAllValues().get(1);
        assertFalse(viewStateSuccess.isLoading());
        assertEquals(TokenAccountViewState.Error.NONE, viewStateSuccess.getError());
        assertEquals(expectedUiModelList, viewStateSuccess.getUiModels());
    }

    @Test
    public void givenUnsuccessfulLoadThenObserveLoadingAndErrorViewStates() {
        // Given
        String address = "address";
        when(getTokenAccountsForEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.error(new Throwable()));

        // When
        classUnderTest.onNavigateToView(address);

        // Then
        ArgumentCaptor<TokenAccountViewState> argumentCaptor = ArgumentCaptor.forClass(TokenAccountViewState.class);
        verify(tokenAccountViewStateObserver, times(2)).onChanged(argumentCaptor.capture());

        TokenAccountViewState viewStateLoading = argumentCaptor.getAllValues().get(0);
        assertTrue(viewStateLoading.isLoading());
        assertEquals(TokenAccountViewState.Error.NONE, viewStateLoading.getError());
        assertTrue(viewStateLoading.getUiModels().isEmpty());

        TokenAccountViewState viewStateSuccess = argumentCaptor.getAllValues().get(1);
        assertFalse(viewStateSuccess.isLoading());
        assertTrue(viewStateSuccess.getError() instanceof TokenAccountViewState.Error.NetworkIssue);
        assertTrue(viewStateSuccess.getUiModels().isEmpty());
    }
}