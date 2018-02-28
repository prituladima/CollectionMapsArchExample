package com.prituladima.collectionmapsarchexample.operations;

public interface LifecycleExecutorProducer {

    LifecycleExecutor getExecutor(int amount, int threads);

}