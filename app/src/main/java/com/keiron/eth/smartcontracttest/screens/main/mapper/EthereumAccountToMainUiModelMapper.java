package com.keiron.eth.smartcontracttest.screens.main.mapper;

import android.content.res.Resources;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.library.common.mapper.BaseMapperToPresentation;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;
import com.keiron.eth.uicomponents.stringformatter.EthBalanceDisplayStringFormatter;

import javax.inject.Inject;

public class EthereumAccountToMainUiModelMapper extends BaseMapperToPresentation<EthereumAccount, MainUiModel> {

    private Resources resources;
    private EthBalanceDisplayStringFormatter ethBalanceDisplayStringFormatter;

    @Inject
    public EthereumAccountToMainUiModelMapper(Resources resources, EthBalanceDisplayStringFormatter ethBalanceDisplayStringFormatter) {
        this.resources = resources;
        this.ethBalanceDisplayStringFormatter = ethBalanceDisplayStringFormatter;
    }

    @Override
    public MainUiModel mapToPresentation(EthereumAccount toBeTransformed) {
        return new MainUiModel(toBeTransformed.getAddress(),
                resources.getString(R.string.eth_balance_format, ethBalanceDisplayStringFormatter.formatForDisplay(toBeTransformed.getBalance())),
                resources.getString(R.string.eth_balance_format, ethBalanceDisplayStringFormatter.formatForDisplay(toBeTransformed.getTokenBalance())));
    }
}
