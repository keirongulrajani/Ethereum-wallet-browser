package com.keiron.eth.smartcontracttest.screens.tokens.mapper;

import android.content.res.Resources;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.library.common.mapper.BaseMapperToPresentation;
import com.keiron.eth.smartcontracttest.R;
import com.keiron.eth.smartcontracttest.screens.tokens.model.TokenAccountUiModel;
import com.keiron.eth.uicomponents.stringformatter.EthBalanceDisplayStringFormatter;

import javax.inject.Inject;

public class TokenAccountToTokenAccountUiModelMapper extends BaseMapperToPresentation<TokenAccount, TokenAccountUiModel> {

    private Resources resources;
    private EthBalanceDisplayStringFormatter ethBalanceDisplayStringFormatter;

    @Inject
    public TokenAccountToTokenAccountUiModelMapper(Resources resources, EthBalanceDisplayStringFormatter ethBalanceDisplayStringFormatter) {

        this.resources = resources;
        this.ethBalanceDisplayStringFormatter = ethBalanceDisplayStringFormatter;
    }

    @Override
    public TokenAccountUiModel mapToPresentation(TokenAccount toBeTransformed) {
        return new TokenAccountUiModel(resources.getString(R.string.smartcontract_title_format, toBeTransformed.getName(), toBeTransformed.getSymbol()),
                ethBalanceDisplayStringFormatter.formatForDisplay(toBeTransformed.getBalance()),
                resources.getString(R.string.eth_balance_format, ethBalanceDisplayStringFormatter.formatForDisplay(toBeTransformed.getBalance().multiply(toBeTransformed.getRate()))));
    }
}
