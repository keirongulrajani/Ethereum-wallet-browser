package com.keiron.eth.domain.conversion.repository;

import io.reactivex.Single;

import java.math.BigDecimal;

public interface ConversionRepository {

    Single<BigDecimal> getConversionRateForEthPair(String tokenSymbol);
}
