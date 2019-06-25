package com.keiron.eth.data.conversion.client;

import com.keiron.eth.data.conversion.model.ConversionRateDto;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConversionClient {


    @GET("/marketinfo/{smartContractSymbol}_ETH")
    Single<ConversionRateDto> getConversionRateForEthPair(@Path(value = "smartContractSymbol") String smartContractSymbol);

}
