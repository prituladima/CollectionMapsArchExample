package com.prituladima.collectionmapsarchexample.util;

import android.util.Log;

public final class Logger {

    private final static boolean IS_LOGGING = false;

    public synchronized static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }

    private Logger(Class c) {
        logTag = c.getSimpleName();
    }

    private final String logTag;


    public void log(String message) {
        if (IS_LOGGING)
            Log.d(logTag, message);
    }

    public void error(Throwable message) {
        if (IS_LOGGING)
            Log.e(logTag, message.toString());
    }

}