package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import com.keiron.eth.library.common.mapper.BaseMapperToDomain;

import javax.inject.Inject;
import java.math.BigDecimal;

public class AccountBalanceDtoToBigDecimalMapper extends BaseMapperToDomain<AccountBalanceDto, BigDecimal> {

    public static final int ETHEREUM_SCALE_FACTOR = 18;

    @Inject
    public AccountBalanceDtoToBigDecimalMapper() {
    }

    @Override
    public BigDecimal mapToDomain(AccountBalanceDto toBeTransformed) {
        return toBeTransformed.getResult().movePointLeft(ETHEREUM_SCALE_FACTOR);
    }
}
