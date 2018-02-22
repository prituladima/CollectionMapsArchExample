package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.MainApplication;
import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.processor.CollectionLogProcessor;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

public class OperationRunnable implements Runnable {

    private OperationParamHolder holder;
    private int amount;

    @Inject
    @Named(LIST_NAME)
    public Repository repository;

    public OperationRunnable(OperationParamHolder holder, int amount) {
        this.holder = holder;
        this.amount = amount;
        MainApplication.getInjector().inject(this);
    }

    @Override
    public void run() {

        repository.put(holder.getPositionInStorage(), 0L, true);
        CollectionLogProcessor processor = CollectionLogProcessor.getPrototype(holder.getImplementation(), holder.getOperationType(), amount);
        Long time = processor.execute();

        repository.put(holder.getPositionInStorage(), time, false);

    }

}