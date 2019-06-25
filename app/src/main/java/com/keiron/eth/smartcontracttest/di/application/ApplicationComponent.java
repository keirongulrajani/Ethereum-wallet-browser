package com.keiron.eth.smartcontracttest.di.application;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import com.keiron.eth.data.accounts.di.AccountModule;
import com.keiron.eth.data.conversion.di.ConversionModule;
import com.keiron.eth.library.network.di.NetworkModule;
import com.keiron.eth.smartcontracttest.di.viewmodel.ViewModelModule;
import com.keiron.eth.smartcontracttest.screens.main.MainFragment;
import dagger.BindsInstance;
import dagger.Component;

import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
@Component(modules = {ApplicationModule.class,
        ViewModelModule.class,
        NetworkModule.class,
        ConversionModule.class,
        AccountModule.class})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);

        ApplicationComponent build();
    }

    void inject(MainFragment mainFragment);

    Map<Class<? extends ViewModel>, Provider<ViewModel>> creators();
}
