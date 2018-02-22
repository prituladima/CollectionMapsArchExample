package com.prituladima.collectionmapsarchexample;

import android.app.Application;

import com.prituladima.collectionmapsarchexample.dagger.ContextModule;
import com.prituladima.collectionmapsarchexample.dagger.DaggerInjector;
import com.prituladima.collectionmapsarchexample.dagger.Injector;


public class MainApplication extends Application {

    private Logger LOGGER = Logger.getLogger(MainApplication.class);

    public static Injector getInjector() {
        return injector;
    }

    private static Injector injector;

    @Override
    public void onCreate() {
        super.onCreate();
        injector = DaggerInjector
                .builder()
                .contextModule(new ContextModule(this))
                .build();


    }

}