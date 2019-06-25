package com.keiron.eth.data.accounts.repository;

import com.keiron.eth.data.accounts.datasource.AccountDataSource;
import com.keiron.eth.data.accounts.mapper.AccountBalanceDtoToBigDecimalMapper;
import com.keiron.eth.data.accounts.mapper.SmartContractDtoToSmartContractMapper;
import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.domain.accounts.repository.AccountRepository;
import io.reactivex.Single;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private AccountDataSource accountDataSource;
    private AccountBalanceDtoToBigDecimalMapper accountBalanceDtoToBigDecimalMapper;
    private SmartContractDtoToSmartContractMapper smartContractDtoToSmartContractMapper;

    @Inject
    public AccountRepositoryImpl(AccountDataSource accountDataSource,
                                 AccountBalanceDtoToBigDecimalMapper accountBalanceDtoToBigDecimalMapper,
                                 SmartContractDtoToSmartContractMapper smartContractDtoToSmartContractMapper) {
        this.accountDataSource = accountDataSource;
        this.accountBalanceDtoToBigDecimalMapper = accountBalanceDtoToBigDecimalMapper;
        this.smartContractDtoToSmartContractMapper = smartContractDtoToSmartContractMapper;
    }

    @Override
    public Single<BigDecimal> getAccountBalance(String address) {
        return accountDataSource.getAccountBalanceForAddress(address)
                .map(accountBalanceDto -> accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto));
    }

    @Override
    public Single<BigDecimal> getSmartContractAccountBalance(String contractAddress, String address) {
        return accountDataSource.getSmartContractAccountBalanceForAddress(contractAddress, address)
                .map(accountBalanceDto -> accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto));
    }

    @Override
    public Single<List<SmartContract>> getListOfSupportedContracts() {
        return accountDataSource.getListOfSupportedContracts()
                .map(smartContractDtos -> smartContractDtoToSmartContractMapper.mapToDomain(smartContractDtos));
    }
}
