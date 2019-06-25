package com.keiron.eth.data.accounts.repository;

import com.keiron.eth.data.accounts.datasource.AccountDataSource;
import com.keiron.eth.data.accounts.mapper.AccountBalanceDtoToBigDecimalMapper;
import com.keiron.eth.data.accounts.mapper.TokenDtoToTokenMapper;
import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import com.keiron.eth.data.accounts.model.TokenDto;
import com.keiron.eth.domain.accounts.model.Token;
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

public class AccountRepositoryImplTest {

    @Mock
    private AccountDataSource accountDataSource;
    @Mock
    private AccountBalanceDtoToBigDecimalMapper accountBalanceDtoToBigDecimalMapper;
    @Mock
    private TokenDtoToTokenMapper tokenDtoToTokenMapper;

    private AccountRepositoryImpl classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new AccountRepositoryImpl(accountDataSource,
                accountBalanceDtoToBigDecimalMapper,
                tokenDtoToTokenMapper);
    }


    @Test
    public void givenAccountAddressWhenGetAccountBalanceThenFetchesAccountBalanceAndMapsToDomain() {
        // Given
        String address = "address";
        AccountBalanceDto accountBalanceDto = mock(AccountBalanceDto.class);
        when(accountDataSource.getAccountBalanceForAddress(address)).thenReturn(Single.just(accountBalanceDto));
        BigDecimal expectedValue = new BigDecimal(1234);
        when(accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto)).thenReturn(expectedValue);

        // When
        TestObserver<BigDecimal> testObserver = classUnderTest.getAccountBalance(address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedValue);
    }

    @Test
    public void givenAccountAddressAndContractAddressWhenGetTokenAccountBalanceThenFetchesTokenAccountBalanceAndMapsToDomain() {
        // Given
        String address = "address";
        String contractAddress = "contract address";
        AccountBalanceDto accountBalanceDto = mock(AccountBalanceDto.class);
        when(accountDataSource.getTokenAccountBalanceForAddress(contractAddress, address)).thenReturn(Single.just(accountBalanceDto));
        BigDecimal expectedValue = new BigDecimal(1234);
        when(accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto)).thenReturn(expectedValue);

        // When
        TestObserver<BigDecimal> testObserver = classUnderTest.getTokenAccountBalance(contractAddress, address).test();

        // Then
        testObserver.assertComplete()
                .assertNoErrors()
                .assertValue(expectedValue);
    }

    @Test
    public void givenListOfSupportedSmartContractsWhenGetListOfSupportedContractsThenReturnsList() {
        // Given
        TokenDto tokenDto = mock(TokenDto.class);
        List<TokenDto> tokenDtos = Collections.singletonList(tokenDto);
        when(accountDataSource.getListOfSupportedTokens()).thenReturn(Single.just(tokenDtos));
        List<Token> expectedTokens = Collections.singletonList(mock(Token.class));
        when(tokenDtoToTokenMapper.mapToDomain(tokenDtos)).thenReturn(expectedTokens);

        // When
        TestObserver<List<Token>> testObserver = classUnderTest.getListOfSupportedTokens().test();

        // Then
        testObserver.assertNoErrors()
                .assertComplete()
                .assertValue(expectedTokens);
    }
}