package com.keiron.eth.smartcontracttest.screens.main.mapper;

import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.library.common.mapper.BaseMapperToPresentation;
import com.keiron.eth.smartcontracttest.screens.main.model.MainUiModel;

public class EthereumAccountToMainUiModelMapper extends BaseMapperToPresentation<EthereumAccount, MainUiModel> {
    @Override
    public MainUiModel mapToPresentation(EthereumAccount toBeTransformed) {
        return new MainUiModel(toBeTransformed.getAddress(),
                toBeTransformed.getBalance(),
                toBeTransformed.getBalance());
    }
}
