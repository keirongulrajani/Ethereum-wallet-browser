package com.keiron.eth.domain.conversion.usecase;

import com.keiron.eth.domain.common.usecase.UseCase;
import com.keiron.eth.domain.conversion.creator.ExchangeRateModelCreator;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.repository.ConversionRepository;
import io.reactivex.Single;

import javax.inject.Inject;

public class GetEthExchangeRateForTokenUseCase extends UseCase<String, Single<ExchangeRate>> {

    private ConversionRepository conversionRepository;
    private ExchangeRateModelCreator exchangeRateModelCreator;

    @Inject
    public GetEthExchangeRateForTokenUseCase(ConversionRepository conversionRepository,
                                             ExchangeRateModelCreator exchangeRateModelCreator) {
        this.conversionRepository = conversionRepository;
        this.exchangeRateModelCreator = exchangeRateModelCreator;
    }

    @Override
    public Single<ExchangeRate> buildUseCase(String tokenSymbol) {
        return conversionRepository.getConversionRateForEthPair(tokenSymbol)
                .map(rate -> exchangeRateModelCreator.create(new ExchangeRateModelCreator.Params(tokenSymbol, rate)));
    }
}
