package com.keiron.eth.smartcontracttest.screens.tokens.mapper;

import android.content.res.Resources;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;
import com.keiron.eth.uicomponents.stringformatter.EthBalanceDisplayStringFormatter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(RobolectricTestRunner.class)
public class TokenAccountToTokenAccountUiModelMapperTest {
    @Mock
    private Resources resources;
    @Mock
    private EthBalanceDisplayStringFormatter ethBalanceDisplayStringFormatter;

    private TokenAccountToTokenAccountUiModelMapper classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new TokenAccountToTokenAccountUiModelMapper(resources, ethBalanceDisplayStringFormatter);
    }

    @Test
    public void givenSmartContractAccountWhenMapToPresentationThenCreatesSmartContractAccountUiModel() {
        // Given
        TokenAccount tokenAccount = mock(TokenAccount.class);
        String name = "name";
        when(tokenAccount.getName()).thenReturn(name);
        String symbol = "symbol";
        when(tokenAccount.getSymbol()).thenReturn(symbol);
        BigDecimal balance = BigDecimal.ONE;
        when(tokenAccount.getBalance()).thenReturn(balance);
        BigDecimal rate = BigDecimal.ONE;
        when(tokenAccount.getRate()).thenReturn(rate);

        String formattedName = "formatted name";
        BigDecimal expectedBalanceInEth = balance.multiply(rate);
        when(resources.getString(R.string.smartcontract_title_format, name, symbol)).thenReturn(formattedName);
        String formattedBalance = "formatted balance";
        when(ethBalanceDisplayStringFormatter.formatForDisplay(expectedBalanceInEth)).thenReturn(formattedBalance);
        String tokenFormattedBalance = "smart contract formatted balance";
        when(resources.getString(R.string.eth_balance_format, formattedBalance)).thenReturn(tokenFormattedBalance);
        // When
        TokenAccountUiModel tokenAccountUiModel = classUnderTest.mapToPresentation(tokenAccount);

        // Then
        assertEquals(formattedName, tokenAccountUiModel.getTitle());
        assertEquals(formattedBalance, tokenAccountUiModel.getBalance());
        assertEquals(tokenFormattedBalance, tokenAccountUiModel.getBalanceInEth());
    }
}