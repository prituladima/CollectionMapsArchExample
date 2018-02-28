package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.constants.TasksInfoStarage;

import java.util.concurrent.CountDownLatch;

public final class OperationExecutorProducer implements LifecycleExecutorProducer {

    private final Repository repository;
    private final TasksInfoStarage storage;

    public OperationExecutorProducer(Repository repository, TasksInfoStarage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public LifecycleExecutor getExecutor(int amount, int threads) {
        return new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(storage.get().size()), threads, amount, repository, storage).build();
    }
}
