package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.SmartContractAccountModelCreator;
import com.keiron.eth.domain.accounts.model.SmartContract;
import com.keiron.eth.domain.common.model.SmartContractAccount;
import com.keiron.eth.domain.common.usecase.UseCase;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.usecase.GetEthExchangeRateForSmartContractUseCase;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class GetSmartContractAccountsForEthereumAccountUseCase extends UseCase<String, Single<List<SmartContractAccount>>> {

    private GetAvailableSmartContractsUseCase getAvailableSmartContractsUseCase;
    private final GetSmartContractAccountBalanceUseCase getSmartContractAccountBalanceUseCase;
    private final GetEthExchangeRateForSmartContractUseCase getEthExchangeRateForSmartContractUseCase;
    private final SmartContractAccountModelCreator smartContractAccountModelCreator;

    @Inject
    public GetSmartContractAccountsForEthereumAccountUseCase(GetAvailableSmartContractsUseCase getAvailableSmartContractsUseCase,
                                                             GetSmartContractAccountBalanceUseCase getSmartContractAccountBalanceUseCase,
                                                             GetEthExchangeRateForSmartContractUseCase getEthExchangeRateForSmartContractUseCase,
                                                             SmartContractAccountModelCreator smartContractAccountModelCreator) {

        this.getAvailableSmartContractsUseCase = getAvailableSmartContractsUseCase;
        this.getSmartContractAccountBalanceUseCase = getSmartContractAccountBalanceUseCase;
        this.getEthExchangeRateForSmartContractUseCase = getEthExchangeRateForSmartContractUseCase;
        this.smartContractAccountModelCreator = smartContractAccountModelCreator;
    }

    @Override
    public Single<List<SmartContractAccount>> buildUseCase(String address) {
        return getAvailableSmartContractsUseCase.buildUseCase()
                .flattenAsObservable((Function<List<SmartContract>, List<SmartContract>>) smartContracts -> smartContracts)
                .flatMapMaybe(smartContract -> getSmartTokenAccountBalance(address, smartContract)
                        .flatMapMaybe((Function<BigDecimal, Maybe<SmartContractAccount>>) smartContractBalance ->
                                smartContractBalance.compareTo(BigDecimal.ZERO) > 0 ? getExchangeRate(smartContract)
                                        .map(exchangeRate -> smartContractAccountModelCreator.create(
                                                new SmartContractAccountModelCreator.Params(address,
                                                        smartContract.getAddress(),
                                                        smartContractBalance,
                                                        smartContract.getName(),
                                                        smartContract.getSymbol(),
                                                        exchangeRate.getRate()))).toMaybe()
                                        : Maybe.empty())
                )
                .toList();
    }

    private Single<ExchangeRate> getExchangeRate(SmartContract smartContract) {
        return getEthExchangeRateForSmartContractUseCase.buildUseCase(smartContract.getSymbol());
    }

    private Single<BigDecimal> getSmartTokenAccountBalance(String address, SmartContract smartContract) {
        return getSmartContractAccountBalanceUseCase.buildUseCase(new GetSmartContractAccountBalanceUseCase.Params(smartContract.getAddress(), address));
    }
}
