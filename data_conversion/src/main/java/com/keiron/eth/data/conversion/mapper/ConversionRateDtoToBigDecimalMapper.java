package com.keiron.eth.data.conversion.mapper;

import com.keiron.eth.data.conversion.model.ConversionRateDto;
import com.keiron.eth.library.common.mapper.BaseMapperToDomain;

import javax.inject.Inject;
import java.math.BigDecimal;

public class ConversionRateDtoToBigDecimalMapper extends BaseMapperToDomain<ConversionRateDto, BigDecimal> {

    @Inject
    public ConversionRateDtoToBigDecimalMapper() {
    }

    @Override
    public BigDecimal mapToDomain(ConversionRateDto toBeTransformed) {
        return toBeTransformed.getRate();
    }
}
