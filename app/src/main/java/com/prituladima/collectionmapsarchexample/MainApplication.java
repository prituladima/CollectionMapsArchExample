package com.prituladima.collectionmapsarchexample;

import android.app.Application;

import com.prituladima.collectionmapsarchexample.dagger.ApplicationComponent;
import com.prituladima.collectionmapsarchexample.dagger.DaggerApplicationComponent;
import com.prituladima.collectionmapsarchexample.util.Logger;


public final class MainApplication extends Application {

    private Logger LOGGER = Logger.getLogger(MainApplication.class);

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LOGGER.log("onCreate");
        applicationComponent = DaggerApplicationComponent.builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}