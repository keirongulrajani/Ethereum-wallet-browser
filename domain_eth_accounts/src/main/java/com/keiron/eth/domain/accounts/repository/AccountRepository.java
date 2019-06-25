package com.keiron.eth.domain.accounts.repository;

import com.keiron.eth.domain.accounts.model.Token;
import io.reactivex.Single;

import java.math.BigDecimal;
import java.util.List;

public interface AccountRepository {

    Single<BigDecimal> getAccountBalance(String address);

    Single<BigDecimal> getTokenAccountBalance(String contractAddress, String address);

    Single<List<Token>> getListOfSupportedTokens();
}
