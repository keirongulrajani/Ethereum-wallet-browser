package com.keiron.eth.data.accounts.di;

import com.google.gson.Gson;
import com.keiron.eth.data.accounts.client.AccountClient;
import com.keiron.eth.data.accounts.datasource.AccountDataSource;
import com.keiron.eth.data.accounts.repository.AccountRepositoryImpl;
import com.keiron.eth.domain.accounts.repository.AccountRepository;

import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Named;

@Module
public class AccountModule {
    private static final String BASE_URL = "https://api.etherscan.io/";
    public static final String API_KEY = "api key";

    @Provides
    @Reusable
    AccountClient provideAccountClient(OkHttpClient okHttpClient, Gson gson) {
        Retrofit restAdapter = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .validateEagerly(true)
                .build();

        return restAdapter.create(AccountClient.class);
    }

    @Provides
    @Reusable
    AccountDataSource provideAccountDataSource(AccountClient accountClient, @Named(API_KEY) String apiKey) {
        return new AccountDataSource(accountClient, apiKey);
    }

    @Provides
    AccountRepository provideAccountRepository(AccountRepositoryImpl accountRepository) {
        return accountRepository;
    }
}


