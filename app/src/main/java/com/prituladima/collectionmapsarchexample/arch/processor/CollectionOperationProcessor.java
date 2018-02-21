package com.prituladima.collectionmapsarchexample.arch.processor;

import com.prituladima.collectionmapsarchexample.arch.exceptions.NoSuchImplementationException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class CollectionOperationProcessor implements
        CollectionProcessor<Integer>,
        ImplementationProvider<List<Integer>>,
        PreparedDataProvider<List<Integer>> {


    private Implementation type;
    private Operation operation;
    private int amount = -1;

    public CollectionOperationProcessor(Implementation type, Operation operation, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public Integer addInTheHead() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < amount; i++) {
            list.add(0, i);
        }
        return amount;
    }

    @Override
    public Integer addInTheTail() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        return amount;
    }

    @Override
    public Integer addInTheMiddle() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < amount; i++) {
            list.add(list.size() / 2, i);
        }
        return amount;
    }

    @Override
    public Integer removeFromTheHead() {
        List<Integer> list = getPreparedData();
        for (int i = 0; i < amount; i++) {
            list.remove(0);
        }
        return amount;
    }

    @Override
    public Integer removeFromTheTail() {
        List<Integer> list = getPreparedData();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() - 1);
        }
        return amount;
    }

    @Override
    public Integer removeFromMiddle() {

        List<Integer> list = getPreparedData();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() / 2);
        }
        return amount;
    }

    @Override
    public Integer search() {
        List<Integer> list = getPreparedData();
        for (int i = 0; i < amount; i++) {
            list.indexOf(1);
        }
        return amount;
    }

    @Override
    public List<Integer> getPreparedData() {
        List<Integer> prepared = getImplementationPrototype();
        for (int i = 0; i < prepared.size(); i++) {
            prepared.add(i);
        }
        return prepared;
    }

    @Override
    public List<Integer> getImplementationPrototype() {
        switch (type) {
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

}