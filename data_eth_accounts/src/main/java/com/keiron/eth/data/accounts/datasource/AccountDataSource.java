package com.keiron.eth.data.accounts.datasource;

import com.keiron.eth.data.accounts.client.AccountClient;
import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import com.keiron.eth.data.accounts.model.TokenDto;
import io.reactivex.Single;

import java.util.Arrays;
import java.util.List;

public class AccountDataSource {

    private AccountClient accountClient;
    private String apiKey;

    public AccountDataSource(AccountClient accountClient, String apiKey) {
        this.accountClient = accountClient;
        this.apiKey = apiKey;
    }

    public Single<AccountBalanceDto> getAccountBalanceForAddress(String address) {
        return accountClient.getAccountBalanceForAddress(address, apiKey);
    }

    public Single<AccountBalanceDto> getTokenAccountBalanceForAddress(String contractAddress, String address) {
        return accountClient.getAccountTokenBalanceForAddress(contractAddress, address, apiKey);
    }

    public Single<List<TokenDto>> getListOfSupportedTokens() {
        return Single.just(Arrays.asList(TokenDto.values()));
    }
}
