package com.keiron.eth.smartcontracttest.screens.main.mapper;

import android.content.res.Resources;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;
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
public class EthereumAccountToMainUiModelMapperTest {

    @Mock
    private Resources resources;

    private EthereumAccountToMainUiModelMapper classUnderTest;

    @Before
    public void setUp() {
        initMocks(this);
        classUnderTest = new EthereumAccountToMainUiModelMapper(resources);
    }

    @Test
    public void givenEthereumAccountWhenMapToPresentationThenCreatesMainUiModel() {
        // Given
        EthereumAccount ethereumAccount = mock(EthereumAccount.class);
        String address = "address";
        when(ethereumAccount.getAddress()).thenReturn(address);
        BigDecimal balance = BigDecimal.ONE;
        when(ethereumAccount.getBalance()).thenReturn(balance);
        BigDecimal smartContractBalance = BigDecimal.TEN;
        when(ethereumAccount.getSmartContractBalance()).thenReturn(smartContractBalance);

        String formattedBalance = "formatted balance";
        when(resources.getString(R.string.eth_balance_format, balance.toPlainString())).thenReturn(formattedBalance);
        String smartContractFormattedBalance = "smart contract formatted balance";
        when(resources.getString(R.string.eth_balance_format, smartContractBalance.toPlainString())).thenReturn(smartContractFormattedBalance);
        // When
        MainUiModel mainUiModel = classUnderTest.mapToPresentation(ethereumAccount);

        // Then
        assertEquals(address, mainUiModel.getAddress());
        assertEquals(formattedBalance, mainUiModel.getEthBalance());
        assertEquals(smartContractFormattedBalance, mainUiModel.getTokenBalance());
    }
}