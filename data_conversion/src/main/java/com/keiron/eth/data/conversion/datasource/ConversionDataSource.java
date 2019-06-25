package com.keiron.eth.data.conversion.datasource;

import com.keiron.eth.data.conversion.client.ConversionClient;
import com.keiron.eth.data.conversion.model.ConversionRateDto;
import io.reactivex.Single;

import javax.inject.Inject;

public class ConversionDataSource {

    private ConversionClient conversionClient;

    @Inject
    public ConversionDataSource(ConversionClient conversionClient) {

        this.conversionClient = conversionClient;
    }

    public Single<ConversionRateDto> getConversionRateForEthPair(String smartContractSymbol) {
        return conversionClient.getConversionRateForEthPair(smartContractSymbol);
    }
}
