package com.keiron.eth.data.accounts.datasource;

import com.keiron.eth.data.accounts.client.AccountClient;
import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AccountDataSourceTest {

    private static final String API_KEY = "api key";

    @Mock
    private AccountClient accountClient;

    private AccountDataSource classUnderTest;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        classUnderTest = new AccountDataSource(accountClient, API_KEY);
    }

    @Test
    public void givenAddressWhenGetAccountBalanceForAddressThenReturnsAccountBalanceDtoFromClient() {
        // Given
        String address = "address";
        AccountBalanceDto accountBalanceDto = mock(AccountBalanceDto.class);
        when(accountClient.getAccountBalanceForAddress(address, API_KEY)).thenReturn(Single.just(accountBalanceDto));

        // When
        TestObserver<AccountBalanceDto> testObserver = classUnderTest.getAccountBalanceForAddress(address).test();

        // Then
        verify(accountClient).getAccountBalanceForAddress(address, API_KEY);
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(accountBalanceDto);
    }

    @Test
    public void givenAddressWhenGetAccountTokenBalanceForAddressThenReturnsAccountBalanceDtoFromClient() {
        // Given
        String address = "address";
        String contractAddress = "contract address";
        AccountBalanceDto accountBalanceDto = mock(AccountBalanceDto.class);
        when(accountClient.getAccountTokenBalanceForAddress(contractAddress, address, API_KEY)).thenReturn(Single.just(accountBalanceDto));

        // When
        TestObserver<AccountBalanceDto> testObserver = classUnderTest.getSmartContractAccountBalanceForAddress(contractAddress, address).test();

        // Then
        verify(accountClient).getAccountTokenBalanceForAddress(contractAddress, address, API_KEY);
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(accountBalanceDto);
    }
}