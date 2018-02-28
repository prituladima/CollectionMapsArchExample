package com.prituladima.collectionmapsarchexample.arch.operations;

public interface LifecycleExecutorProducer {

    LifecycleExecutor getExecutor(int amount, int threads);

}