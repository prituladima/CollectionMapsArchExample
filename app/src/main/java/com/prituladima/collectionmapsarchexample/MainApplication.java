package com.prituladima.collectionmapsarchexample;

import android.app.Application;

import com.prituladima.collectionmapsarchexample.dagger.ApplicationComponent;
import com.prituladima.collectionmapsarchexample.util.Logger;

import static com.prituladima.collectionmapsarchexample.dagger.DaggerApplicationComponent.builder;

public final class MainApplication extends Application {

    private final static Logger LOGGER = Logger.getLogger(MainApplication.class);

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        LOGGER.log("onCreate");
        applicationComponent = builder().build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}