package com.prituladima.collectionmapsarchexample;

import android.app.Application;

import com.prituladima.collectionmapsarchexample.dagger.ApplicationComponent;

import static com.prituladima.collectionmapsarchexample.dagger.DaggerApplicationComponent.builder;

public final class MainApplication extends Application {

  private static final Logger LOGGER = Logger.getLogger(MainApplication.class);

  private static ApplicationComponent applicationComponent;

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    LOGGER.log("onCreate");
    applicationComponent = builder().build();
  }
}
