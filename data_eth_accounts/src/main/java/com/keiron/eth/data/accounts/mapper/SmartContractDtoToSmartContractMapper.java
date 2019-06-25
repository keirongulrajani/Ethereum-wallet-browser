package com.keiron.eth.data.accounts.mapper;

import com.keiron.eth.data.accounts.model.SmartContractDto;
import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.library.common.mapper.BaseMapperToDomain;

import javax.inject.Inject;

public class SmartContractDtoToSmartContractMapper extends BaseMapperToDomain<SmartContractDto, SmartContract> {

    @Inject
    public SmartContractDtoToSmartContractMapper() {

    }

    @Override
    public SmartContract mapToDomain(SmartContractDto toBeTransformed) {
        return new SmartContract(toBeTransformed.getAddress(), toBeTransformed.getName(), toBeTransformed.getSymbol());
    }
}
