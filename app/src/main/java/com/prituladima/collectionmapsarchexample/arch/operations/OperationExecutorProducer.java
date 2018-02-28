package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.arch.constants.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.concurrent.CountDownLatch;

public class OperationExecutorProducer implements LifecycleExecutorProducer {

    private final Repository repository;
    private final OperationDataStorage storage;

    public OperationExecutorProducer(Repository repository, OperationDataStorage storage) {
        this.repository = repository;
        this.storage = storage;
    }

    @Override
    public LifecycleExecutor getExecutor(int amount, int threads) {
        return new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(storage.get().size()), threads, amount, repository, storage).build();
    }
}
