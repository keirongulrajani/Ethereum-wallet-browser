package com.keiron.eth.data.conversion.repository;

import com.keiron.eth.data.conversion.datasource.ConversionDataSource;
import com.keiron.eth.data.conversion.mapper.ConversionRateDtoToBigDecimalMapper;
import com.keiron.eth.domain.conversion.repository.ConversionRepository;
import io.reactivex.Single;

import javax.inject.Inject;
import java.math.BigDecimal;

public class ConversionRepositoryImpl implements ConversionRepository {

    private ConversionDataSource conversionDataSource;
    private ConversionRateDtoToBigDecimalMapper conversionRateDtoToBigDecimalMapper;

    @Inject
    public ConversionRepositoryImpl(ConversionDataSource conversionDataSource,
                                    ConversionRateDtoToBigDecimalMapper conversionRateDtoToBigDecimalMapper) {
        this.conversionDataSource = conversionDataSource;
        this.conversionRateDtoToBigDecimalMapper = conversionRateDtoToBigDecimalMapper;
    }

    @Override
    public Single<BigDecimal> getConversionRateForEthPair(String smartContractSymbol) {
        return conversionDataSource.getConversionRateForEthPair(smartContractSymbol)
                .map(conversionRateDto -> conversionRateDtoToBigDecimalMapper.mapToDomain(conversionRateDto));
    }
}
