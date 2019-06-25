package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.model.Token;
import com.keiron.eth.domain.accounts.repository.AccountRepository;
import com.keiron.eth.domain.common.usecase.UseCaseVoid;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

public class GetAvailableTokensUseCase extends UseCaseVoid<Single<List<Token>>> {

    private AccountRepository accountRepository;

    @Inject
    public GetAvailableTokensUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Single<List<Token>> buildUseCase() {
        return accountRepository.getListOfSupportedTokens();
    }
}
