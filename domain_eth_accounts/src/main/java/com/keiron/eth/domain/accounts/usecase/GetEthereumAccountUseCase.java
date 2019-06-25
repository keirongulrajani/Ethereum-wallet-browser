package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.EthereumAccountModelCreator;
import com.keiron.eth.domain.common.model.EthereumAccount;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.domain.common.usecase.UseCase;
import io.reactivex.Single;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class GetEthereumAccountUseCase extends UseCase<String, Single<EthereumAccount>> {

    private final GetAccountBalanceUseCase getAccountBalanceUseCase;
    private GetTokenAccountsForEthereumAccountUseCase getTokenAccountsForEthereumAccountUseCase;
    private final EthereumAccountModelCreator ethereumAccountModelCreator;

    @Inject
    public GetEthereumAccountUseCase(GetAccountBalanceUseCase getAccountBalanceUseCase,
                                     GetTokenAccountsForEthereumAccountUseCase getTokenAccountsForEthereumAccountUseCase,
                                     EthereumAccountModelCreator ethereumAccountModelCreator) {
        this.getAccountBalanceUseCase = getAccountBalanceUseCase;
        this.getTokenAccountsForEthereumAccountUseCase = getTokenAccountsForEthereumAccountUseCase;
        this.ethereumAccountModelCreator = ethereumAccountModelCreator;
    }

    @Override
    public Single<EthereumAccount> buildUseCase(String address) {
        return Single.zip(getAccountBalanceUseCase.buildUseCase(address),
                getTokenAccountsForEthereumAccountUseCase.buildUseCase(address),
                (ethBalance, tokenAccounts) -> ethereumAccountModelCreator.create(new EthereumAccountModelCreator.Params(address, ethBalance, getTotalTokenBalanceInEth(tokenAccounts), tokenAccounts)));
    }

    private BigDecimal getTotalTokenBalanceInEth(List<TokenAccount> tokenAccounts) {
        BigDecimal total = BigDecimal.ZERO;
        for (TokenAccount tokenAccount : tokenAccounts) {
            total = total.add(tokenAccount.getBalance().multiply(tokenAccount.getRate()));
        }
        return total;
    }
}
