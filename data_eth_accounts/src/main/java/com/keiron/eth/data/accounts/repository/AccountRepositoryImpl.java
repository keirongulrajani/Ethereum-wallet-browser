package com.keiron.eth.data.accounts.repository;

import com.keiron.eth.data.accounts.datasource.AccountDataSource;
import com.keiron.eth.data.accounts.mapper.AccountBalanceDtoToBigDecimalMapper;
import com.keiron.eth.data.accounts.mapper.TokenDtoToTokenMapper;
import com.keiron.eth.domain.accounts.model.Token;
import com.keiron.eth.domain.accounts.repository.AccountRepository;
import io.reactivex.Single;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class AccountRepositoryImpl implements AccountRepository {

    private AccountDataSource accountDataSource;
    private AccountBalanceDtoToBigDecimalMapper accountBalanceDtoToBigDecimalMapper;
    private TokenDtoToTokenMapper tokenDtoToTokenMapper;

    @Inject
    public AccountRepositoryImpl(AccountDataSource accountDataSource,
                                 AccountBalanceDtoToBigDecimalMapper accountBalanceDtoToBigDecimalMapper,
                                 TokenDtoToTokenMapper tokenDtoToTokenMapper) {
        this.accountDataSource = accountDataSource;
        this.accountBalanceDtoToBigDecimalMapper = accountBalanceDtoToBigDecimalMapper;
        this.tokenDtoToTokenMapper = tokenDtoToTokenMapper;
    }

    @Override
    public Single<BigDecimal> getAccountBalance(String address) {
        return accountDataSource.getAccountBalanceForAddress(address)
                .map(accountBalanceDto -> accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto));
    }

    @Override
    public Single<BigDecimal> getTokenAccountBalance(String contractAddress, String address) {
        return accountDataSource.getTokenAccountBalanceForAddress(contractAddress, address)
                .map(accountBalanceDto -> accountBalanceDtoToBigDecimalMapper.mapToDomain(accountBalanceDto));
    }

    @Override
    public Single<List<Token>> getListOfSupportedTokens() {
        return accountDataSource.getListOfSupportedTokens()
                .map(tokenDtos -> tokenDtoToTokenMapper.mapToDomain(tokenDtos));
    }
}
