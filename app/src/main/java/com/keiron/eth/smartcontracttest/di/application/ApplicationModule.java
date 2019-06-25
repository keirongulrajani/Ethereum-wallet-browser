package com.keiron.eth.smartcontracttest.di.application;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import com.keiron.eth.library.common.schedulers.SchedulersProvider;
import com.keiron.eth.smartcontracttest.BuildConfig;
import com.keiron.eth.smartcontracttest.application.EthereumTokenApplication;
import com.keiron.eth.smartcontracttest.schedulers.SchedulersProviderImpl;
import dagger.Module;
import dagger.Provides;
import dagger.Reusable;

import javax.inject.Named;
import javax.inject.Singleton;

import static com.keiron.eth.data.accounts.di.AccountModule.API_KEY;

@Module
public class ApplicationModule {

    @Provides
    @Singleton
    EthereumTokenApplication provideApplication(Application application) {
        return ((EthereumTokenApplication) application);
    }

    @Provides
    @Singleton
    Context provideApplicationContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Resources provideResources(Context context) {
        return context.getResources();
    }

    @Provides
    @Reusable
    SchedulersProvider providesSchedulers(SchedulersProviderImpl schedulersProviderImpl) {
        return schedulersProviderImpl;
    }


    @Provides
    @Named(API_KEY)
    String proveideApiKey() {
        return BuildConfig.EtherscanApiKey;
    }
}
