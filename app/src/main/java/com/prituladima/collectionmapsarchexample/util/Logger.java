package com.prituladima.collectionmapsarchexample.util;

import android.util.Log;

public final class Logger {

    public synchronized static Logger getLogger(Class<?> clazz) {
        return new Logger(clazz);
    }

    private Logger(Class c) {
        logTag = c.getSimpleName();
    }

    private final String logTag;
    private final boolean isLogging = true;

    public void log(String message) {
//        if (isLogging)
//            Log.d(logTag, message);
        System.out.println(message);
    }

    public void error(Throwable message) {
        if (isLogging)
            Log.e(logTag, message.toString());
    }

}