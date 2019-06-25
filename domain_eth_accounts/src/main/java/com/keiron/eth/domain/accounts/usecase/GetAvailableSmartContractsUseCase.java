package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.domain.accounts.repository.AccountRepository;
import com.keiron.eth.domain.common.usecase.UseCaseVoid;
import io.reactivex.Single;

import javax.inject.Inject;
import java.util.List;

public class GetAvailableSmartContractsUseCase extends UseCaseVoid<Single<List<SmartContract>>> {

    private AccountRepository accountRepository;

    @Inject
    public GetAvailableSmartContractsUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Single<List<SmartContract>> buildUseCase() {
        return accountRepository.getListOfSupportedContracts();
    }
}
