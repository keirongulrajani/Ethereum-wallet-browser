package com.keiron.eth.domain.conversion.usecase;

import com.keiron.eth.domain.common.usecase.UseCase;
import com.keiron.eth.domain.conversion.creator.ExchangeRateModelCreator;
import com.keiron.eth.domain.conversion.model.ExchangeRate;
import com.keiron.eth.domain.conversion.repository.ConversionRepository;
import io.reactivex.Single;

import javax.inject.Inject;

public class GetEthExchangeRateForSmartContractUseCase extends UseCase<String, Single<ExchangeRate>> {

    private ConversionRepository conversionRepository;
    private ExchangeRateModelCreator exchangeRateModelCreator;

    @Inject
    public GetEthExchangeRateForSmartContractUseCase(ConversionRepository conversionRepository,
                                                     ExchangeRateModelCreator exchangeRateModelCreator) {
        this.conversionRepository = conversionRepository;
        this.exchangeRateModelCreator = exchangeRateModelCreator;
    }

    @Override
    public Single<ExchangeRate> buildUseCase(String smartContractSymbol) {
        return conversionRepository.getConversionRateForEthPair(smartContractSymbol)
                .map(rate -> exchangeRateModelCreator.create(new ExchangeRateModelCreator.Params(smartContractSymbol, rate)));
    }
}
