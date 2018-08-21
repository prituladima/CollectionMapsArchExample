package com.prituladima.collectionmapsarchexample;

import static android.util.Log.*;

public final class Logger {

  private static final boolean IS_LOGGING = false;
  private final String logTag;

  private Logger(Class c) {
    logTag = c.getSimpleName();
  }

  public static synchronized Logger getLogger(Class<?> clazz) {
    return new Logger(clazz);
  }

  public void log(String message) {
    if (IS_LOGGING) d(logTag, message);
  }

  public void error(Throwable message) {
    if (IS_LOGGING) e(logTag, message.toString());
  }
}
