package com.keiron.eth.domain.accounts.usecase;

import com.keiron.eth.domain.accounts.creator.TokenAccountModelCreator;
import com.keiron.eth.domain.accounts.model.Token;
import com.keiron.eth.domain.common.model.TokenAccount;
import com.keiron.eth.domain.common.usecase.UseCase;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.usecase.GetEthExchangeRateForTokenUseCase;
import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.functions.Function;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.List;

public class GetTokenAccountsForEthereumAccountUseCase extends UseCase<String, Single<List<TokenAccount>>> {

    private GetAvailableTokensUseCase getAvailableTokensUseCase;
    private final GetTokenAccountBalanceUseCase getTokenAccountBalanceUseCase;
    private final GetEthExchangeRateForTokenUseCase getEthExchangeRateForTokenUseCase;
    private final TokenAccountModelCreator tokenAccountModelCreator;

    @Inject
    public GetTokenAccountsForEthereumAccountUseCase(GetAvailableTokensUseCase getAvailableTokensUseCase,
                                                     GetTokenAccountBalanceUseCase getTokenAccountBalanceUseCase,
                                                     GetEthExchangeRateForTokenUseCase getEthExchangeRateForTokenUseCase,
                                                     TokenAccountModelCreator tokenAccountModelCreator) {

        this.getAvailableTokensUseCase = getAvailableTokensUseCase;
        this.getTokenAccountBalanceUseCase = getTokenAccountBalanceUseCase;
        this.getEthExchangeRateForTokenUseCase = getEthExchangeRateForTokenUseCase;
        this.tokenAccountModelCreator = tokenAccountModelCreator;
    }

    @Override
    public Single<List<TokenAccount>> buildUseCase(String address) {
        return getAvailableTokensUseCase.buildUseCase()
                .flattenAsObservable((Function<List<Token>, List<Token>>) tokens -> tokens)
                .flatMapMaybe(token -> getTokenAccountBalance(address, token)
                        .flatMapMaybe((Function<BigDecimal, Maybe<TokenAccount>>) tokenBalance ->
                                tokenBalance.compareTo(BigDecimal.ZERO) > 0 ? getExchangeRate(token)
                                        .map(exchangeRate -> tokenAccountModelCreator.create(
                                                new TokenAccountModelCreator.Params(address,
                                                        token.getAddress(),
                                                        tokenBalance,
                                                        token.getName(),
                                                        token.getSymbol(),
                                                        exchangeRate.getRate()))).toMaybe()
                                        : Maybe.empty())
                )
                .toList();
    }

    private Single<ExchangeRate> getExchangeRate(Token token) {
        return getEthExchangeRateForTokenUseCase.buildUseCase(token.getSymbol());
    }

    private Single<BigDecimal> getTokenAccountBalance(String address, Token token) {
        return getTokenAccountBalanceUseCase.buildUseCase(new GetTokenAccountBalanceUseCase.Params(token.getAddress(), address));
    }
}
