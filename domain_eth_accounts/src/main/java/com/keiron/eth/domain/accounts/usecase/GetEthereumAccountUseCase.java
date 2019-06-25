package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.EthereumAccountModelCreator;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.usecase.UseCase;
import io.reactivex.Single;

import javax.inject.Inject;

public class GetEthereumAccountUseCase extends UseCase<String, Single<EthereumAccount>> {

    private final GetAccountBalanceUseCase getAccountBalanceUseCase;
    private GetSmartContractAccountsForEthereumAccountUseCase getSmartContractAccountsForEthereumAccountUseCase;
    private final EthereumAccountModelCreator ethereumAccountModelCreator;

    @Inject
    public GetEthereumAccountUseCase(GetAccountBalanceUseCase getAccountBalanceUseCase,
                                     GetSmartContractAccountsForEthereumAccountUseCase getSmartContractAccountsForEthereumAccountUseCase,
                                     EthereumAccountModelCreator ethereumAccountModelCreator) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
        this.getSmartContractAccountsForEthereumAccountUseCase = getSmartContractAccountsForEthereumAccountUseCase;
        this.ethereumAccountModelCreator = ethereumAccountModelCreator;
    }

    @Override
    public Single<EthereumAccount> buildUseCase(String address) {
        return Single.zip(getAccountBalanceUseCase.buildUseCase(address),
                getSmartContractAccountsForEthereumAccountUseCase.buildUseCase(address),
                (ethBalance, tokenAccounts) -> ethereumAccountModelCreator.create(new EthereumAccountModelCreator.Params(address, ethBalance, tokenAccounts)));
    }
}
