package com.keiron.eth.library.network.di;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import javax.inject.Named;
import javax.inject.Singleton;
import java.util.concurrent.TimeUnit;


@Module
public class NetworkModule {
    private static final String TIMEOUT_IN_SECONDS = "timeoutInSeconds";
    private static final int TIMEOUT_IN_SEC = 15;

    @Provides
    @Named(TIMEOUT_IN_SECONDS)
    int provideNetworkTimeout() {
        return TIMEOUT_IN_SEC;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Context application) {
        long cacheSize = 20 * 1024 * 1024; // 20 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache,
                                     @Named(TIMEOUT_IN_SECONDS) int networkTimeoutInSeconds) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.connectTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS);
        client.readTimeout(networkTimeoutInSeconds, TimeUnit.SECONDS);
        client.cache(cache);
        client.addInterceptor(new HttpLoggingInterceptor());

        return client.build();
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().setLenient().create();
    }
}