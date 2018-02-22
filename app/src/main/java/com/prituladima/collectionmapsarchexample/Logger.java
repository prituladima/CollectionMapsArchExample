package com.prituladima.collectionmapsarchexample;

import android.util.Log;

public class Logger {

    public synchronized static Logger getLogger(Class<?> clazz){
        return new Logger(clazz);
    }

    private Logger(Class c) {
        logTag = c.getSimpleName();
    }

    private String logTag;
    private boolean isLogging = true;

    public void log(String message) {
        if (isLogging)
            Log.d(logTag, message);
//        System.out.println(message);
    }

    public void error(Throwable message) {
        if (isLogging)
            Log.e(logTag, message.toString());
    }

}