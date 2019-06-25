package com.keiron.eth.smartcontracttest.screens.main.mapper;

import android.content.res.Resources;

import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.library.common.mapper.BaseMapperToPresentation;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;

import javax.inject.Inject;

public class EthereumAccountToMainUiModelMapper extends BaseMapperToPresentation<EthereumAccount, MainUiModel> {

    private Resources resources;

    @Inject
    public EthereumAccountToMainUiModelMapper(Resources resources) {
        this.resources = resources;
    }

    @Override
    public MainUiModel mapToPresentation(EthereumAccount toBeTransformed) {
        return new MainUiModel(toBeTransformed.getAddress(),
                resources.getString(R.string.eth_balance_format, toBeTransformed.getBalance().toPlainString()),
                resources.getString(R.string.eth_balance_format, toBeTransformed.getSmartContractBalance().toPlainString()));
    }
}
