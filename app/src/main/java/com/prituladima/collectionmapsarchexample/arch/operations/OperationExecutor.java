package com.prituladima.collectionmapsarchexample.arch.operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by prituladima on 2/23/18.
 */

public class OperationExecutor {

    private ExecutorService executorService;

    public void start() {
        executorService = Executors.newSingleThreadExecutor();
    }

    public void stop() {
        executorService.shutdown();
    }

}
