package com.prituladima.collectionmapsarchexample;

import android.app.Application;

import com.prituladima.collectionmapsarchexample.dagger.DaggerInjector;
import com.prituladima.collectionmapsarchexample.dagger.Injector;


public class MainApplication extends Application {

    private Logger LOGGER = Logger.getLogger(MainApplication.class);

    private Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        LOGGER.log("onCreate");
        injector = DaggerInjector.builder().build();
    }

    public Injector getApplicationInjector() {
        return injector;
    }
}