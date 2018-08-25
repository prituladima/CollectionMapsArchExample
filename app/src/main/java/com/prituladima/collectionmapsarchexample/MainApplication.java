package com.prituladima.collectionmapsarchexample;

import android.app.Application;

public final class MainApplication extends Application {

  private static final Logger LOGGER = Logger.getLogger(MainApplication.class);

  @Override
  public void onCreate() {
    super.onCreate();
    LOGGER.log("onCreate");
  }
}
