package com.keiron.eth.smartcontracttest.screens.main;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;
import com.keiron.eth.domain.accounts.usecase.GetEthereumAccountUseCase;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import com.keiron.eth.smartcontracttest.screens.main.mapper.EthereumAccountToMainUiModelMapper;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;
import com.keiron.eth.smartcontracttest.screens.main.model.MainViewState;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MainViewModelTest {

    @Rule
    public TestRule testRule = new InstantTaskExecutorRule();

    @Mock
    private GetEthereumAccountUseCase getEthereumAccountUseCase;
    @Mock
    private EthereumAccountToMainUiModelMapper ethereumAccountToMainUiModelMapper;
    @Mock
    private SchedulersProvider schedulersProvider;
    @Mock
    private Observer<MainViewState> mainViewStateObserver;

    private MainViewModel classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);

        when(schedulersProvider.io()).thenReturn(Schedulers.trampoline());
        when(schedulersProvider.mainThread()).thenReturn(Schedulers.trampoline());

        classUnderTest = new MainViewModel(getEthereumAccountUseCase, ethereumAccountToMainUiModelMapper, schedulersProvider);

        classUnderTest.mainViewState.observeForever(mainViewStateObserver);
    }

    @Test
    public void givenAddressWhenOnEthAddressSubmittedThenFetchesEthAccountAndMapsToPresentation() {
        // Given
        String address = "address";
        EthereumAccount ethereumAccount = mock(EthereumAccount.class);
        when(getEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(ethereumAccount));
        MainUiModel expectedMainUiModel = mock(MainUiModel.class);
        when(ethereumAccountToMainUiModelMapper.mapToPresentation(ethereumAccount)).thenReturn(expectedMainUiModel);

        // When
        classUnderTest.onEthAddressSubmitted(address);

        // Then
        verify(getEthereumAccountUseCase).buildUseCase(address);
        verify(ethereumAccountToMainUiModelMapper).mapToPresentation(ethereumAccount);
    }

    @Test
    public void givenAddressWhenOnEthAddressSubmittedThenObserveLoadingAndSuccessViewStates() {
        // Given
        String address = "address";
        EthereumAccount ethereumAccount = mock(EthereumAccount.class);
        when(getEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.just(ethereumAccount));
        MainUiModel expectedMainUiModel = mock(MainUiModel.class);
        when(ethereumAccountToMainUiModelMapper.mapToPresentation(ethereumAccount)).thenReturn(expectedMainUiModel);

        // When
        classUnderTest.onEthAddressSubmitted(address);

        // Then
        ArgumentCaptor<MainViewState> argumentCaptor = ArgumentCaptor.forClass(MainViewState.class);
        verify(mainViewStateObserver, times(2)).onChanged(argumentCaptor.capture());

        MainViewState viewStateLoading = argumentCaptor.getAllValues().get(0);
        assertTrue(viewStateLoading.isLoading());
        assertEquals(MainViewState.Error.NONE, viewStateLoading.getError());
        assertNull(viewStateLoading.getMainUiModel());

        MainViewState viewStateSuccess = argumentCaptor.getAllValues().get(1);
        assertFalse(viewStateSuccess.isLoading());
        assertEquals(MainViewState.Error.NONE, viewStateSuccess.getError());
        assertEquals(expectedMainUiModel, viewStateSuccess.getMainUiModel());
    }

    @Test
    public void givenUnsuccessfulLoadThenObserveLoadingAndErrorViewStates() {
        // Given
        String address = "address";
        EthereumAccount ethereumAccount = mock(EthereumAccount.class);
        when(getEthereumAccountUseCase.buildUseCase(address)).thenReturn(Single.error(new Throwable()));

        // When
        classUnderTest.onEthAddressSubmitted(address);

        // Then
        ArgumentCaptor<MainViewState> argumentCaptor = ArgumentCaptor.forClass(MainViewState.class);
        verify(mainViewStateObserver, times(2)).onChanged(argumentCaptor.capture());

        MainViewState viewStateLoading = argumentCaptor.getAllValues().get(0);
        assertTrue(viewStateLoading.isLoading());
        assertEquals(MainViewState.Error.NONE, viewStateLoading.getError());
        assertNull(viewStateLoading.getMainUiModel());

        MainViewState viewStateSuccess = argumentCaptor.getAllValues().get(1);
        assertFalse(viewStateSuccess.isLoading());
        assertTrue(viewStateSuccess.getError() instanceof MainViewState.Error.NetworkIssue);
        assertNull(viewStateSuccess.getMainUiModel());
    }
}