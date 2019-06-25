package com.keiron.eth.smartcontracttest.application;

import androidx.multidex.MultiDexApplication;
import com.keiron.eth.smartcontracttest.di.application.ApplicationComponentHolder;

public class EthereumTokenApplication extends MultiDexApplication {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationComponentHolder.getInstance().create(new ApplicationComponentHolder.Factory(this));
    }
}
