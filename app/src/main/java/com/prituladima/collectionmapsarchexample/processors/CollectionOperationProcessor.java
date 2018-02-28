package com.prituladima.collectionmapsarchexample.processors;

import com.prituladima.collectionmapsarchexample.constants.TaskInfo;
import com.prituladima.collectionmapsarchexample.exceptions.NoSuchImplementationException;
import com.prituladima.collectionmapsarchexample.exceptions.NoSuchOperationException;
import com.prituladima.collectionmapsarchexample.processor.CollectionProcessor;
import com.prituladima.collectionmapsarchexample.processor.ImplementationProvider;
import com.prituladima.collectionmapsarchexample.processor.PreparedDataProvider;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.prituladima.collectionmapsarchexample.constants.ListImplementation;
import com.prituladima.collectionmapsarchexample.constants.ListOperation;
import static com.prituladima.collectionmapsarchexample.util.TimeUtil.getNow;

public class CollectionOperationProcessor implements
        CollectionProcessor,
        ImplementationProvider<List<Integer>>,
        PreparedDataProvider<List<Integer>> {

    private final ListImplementation implementation;
    private final ListOperation operation;
    private final int amount;

    public CollectionOperationProcessor(TaskInfo holder, int amount) {
        this.implementation = (ListImplementation) holder.getImplementation();
        this.amount = amount;
        this.operation = (ListOperation) holder.getOperationType();
    }

    @Override
    public long addInTheHead() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(0, i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long addInTheTail() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long addInTheMiddle() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(list.size() / 2, i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long removeFromTheHead() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(0);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long removeFromTheTail() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() - 1);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long removeFromMiddle() {

        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() / 2);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public long search() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.indexOf(1);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public List<Integer> getPreparedData() {
        List<Integer> prepared = getImplementationPrototype();
        for (int i = 0; i < amount; i++) {
            prepared.add(i);
        }
        return prepared;
    }

    @Override
    public List<Integer> getImplementationPrototype() {
        switch (implementation) {
            case ARRAY_LIST:
                return new ArrayList<>();
            case LINKED_LIST:
                return new LinkedList<>();
            case COW_ARRAY_LIST:
                return new CopyOnWriteArrayList<>();
            default:
                throw new NoSuchImplementationException();
        }
    }

    @Override
    public long execute() {
        switch (operation) {
            case ADD_IN_THE_HEAD:
                return addInTheHead();
            case ADD_IN_THE_MIDDLE:
                return addInTheMiddle();
            case ADD_IN_THE_TAIL:
                return addInTheTail();

            case REMOVE_FROM_HEAD:
                return removeFromTheHead();
            case REMOVE_FROM_MIDDLE:
                return removeFromMiddle();
            case REMOVE_FROM_TAIL:
                return removeFromTheTail();
            case SEARCH:
                return search();
            default:
                throw new NoSuchOperationException();
        }
    }

}