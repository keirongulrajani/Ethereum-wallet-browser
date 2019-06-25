package com.keiron.eth.data.conversion.di;

import com.google.gson.Gson;
import com.keiron.eth.data.conversion.client.ConversionClient;
import com.keiron.eth.data.conversion.repository.ConversionRepositoryImpl;
import com.keiron.eth.domain.conversion.repository.ConversionRepository;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ConversionModule {

    private static final String BASE_URL = "https://shapeshift.io";

    @Provides
    @Reusable
    ConversionClient provideConversionClient(OkHttpClient okHttpClient, Gson gson) {
        Retrofit restAdapter = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .validateEagerly(true)
                .build();

        return restAdapter.create(ConversionClient.class);
    }

    @Provides
    ConversionRepository provideConversionRepository(ConversionRepositoryImpl conversionRepository) {
        return conversionRepository;
    }
}
