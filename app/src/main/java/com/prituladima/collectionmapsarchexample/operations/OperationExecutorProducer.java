package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;

import java.util.concurrent.CountDownLatch;

public final class OperationExecutorProducer implements LifecycleExecutorProducer {

    private final Repository repository;
    private final TasksInfoStorage storage;
    private final ProcessorFactory factory;

    public OperationExecutorProducer(Repository repository, TasksInfoStorage storage, ProcessorFactory factory) {
        this.repository = repository;
        this.storage = storage;
        this.factory = factory;
    }

    @Override
    public LifecycleExecutor getExecutor(int amount, int threads) {
        return new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(storage.get().size()), threads, amount, repository, storage, factory).build();
    }
}
