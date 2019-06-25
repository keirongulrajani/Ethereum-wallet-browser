package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.repository.AccountRepository;
import com.keiron.eth.domain.common.usecase.UseCase;
import io.reactivex.Single;

import javax.inject.Inject;
import java.math.BigDecimal;

public class GetAccountBalanceUseCase extends UseCase<String, Single<BigDecimal>> {

    private AccountRepository accountRepository;

    @Inject
    public GetAccountBalanceUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Single<BigDecimal> buildUseCase(String address) {
        return accountRepository.getAccountBalance(address);
    }
}
