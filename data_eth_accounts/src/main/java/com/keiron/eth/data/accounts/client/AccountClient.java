package com.keiron.eth.data.accounts.client;

import com.keiron.eth.data.accounts.model.AccountBalanceDto;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface AccountClient {

    @GET("module=account&action=balance&tag=latest")
    Single<AccountBalanceDto> getAccountBalanceForAddress(@Query(value = "address") String address,
                                                          @Query(value = "apikey") String apikey);

    @GET("module=account&action=tokenbalance&tag=latest")
    Single<AccountBalanceDto> getAccountTokenBalanceForAddress(@Query(value = "contractaddress") String contractAddress,
                                                               @Query(value = "address") String address,
                                                               @Query(value = "apikey") String apikey);

}