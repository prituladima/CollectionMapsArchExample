package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.processor.CollectionLogProcessor;

public class OperationRunnable implements Runnable {

    private OperationParamHolder holder;
    private int amount;

    public OperationRunnable(OperationParamHolder holder, int amount) {
        this.holder = holder;
        this.amount = amount;
    }

    @Override
    public void run() {

        CollectionLogProcessor processor = CollectionLogProcessor.getPrototype(holder.getImplementation(), holder.getOperationType(), amount);
        processor.execute();

    }

}