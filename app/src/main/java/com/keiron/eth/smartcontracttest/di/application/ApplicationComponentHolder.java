package com.keiron.eth.smartcontracttest.di.application;

import android.app.Application;

public class ApplicationComponentHolder {
    private static ApplicationComponentHolder instance;

    private ApplicationComponent applicationComponent;

    public static ApplicationComponentHolder getInstance() {
        if (instance == null) {
            instance = new ApplicationComponentHolder();
        }
        return instance;
    }

    public ApplicationComponent create(Factory factory) {
        applicationComponent = factory.build();

        return applicationComponent;
    }

    public void destroy() {
        applicationComponent = null;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    protected Class<ApplicationComponent> getComponentClass() {
        return ApplicationComponent.class;
    }

    public static class Factory {

        private Application application;

        public Factory(Application application) {

            this.application = application;
        }

        public ApplicationComponent build() {
            return DaggerApplicationComponent.builder()
                    .application(application)
                    .build();
        }
    }
}
