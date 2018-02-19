package com.prituladima.collectionmapsarchexample.arch.processor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class CollectionOperationProcessor implements CollectionProcessor<Integer>, Preparable {


    private Implementation type;
    private Operation operation;
    private int amount = -1;
    private List<Integer> prepared;

    public CollectionOperationProcessor(Implementation type, Operation operation,  int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public Integer addInTheHead() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < getAmount(); i++) {
            list.add(0, i);
        }
        return amount;
    }

    @Override
    public Integer addInTheTail() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < getAmount(); i++) {
            list.add(i);
        }
        return amount;
    }

    @Override
    public Integer addInTheMiddle() {
        List<Integer> list = getImplementationPrototype();
        for (int i = 0; i < getAmount(); i++) {
            list.add(list.size() / 2, i);
        }
        return amount;
    }

    @Override
    public Integer removeFromTheHead() {
        List<Integer> list = getPrepared();
        for (int i = 0; i < getAmount(); i++) {
            list.remove(0);
        }
        return amount;
    }

    @Override
    public Integer removeFromTheTail() {
        List<Integer> list = getPrepared();
        for (int i = 0; i < getAmount(); i++) {
            list.remove(list.size() - 1);
        }
        return amount;
    }

    @Override
    public Integer removeFromMiddle() {

        List<Integer> list = getPrepared();
        for (int i = 0; i < getAmount(); i++) {
            list.remove(list.size() / 2);
        }
        return amount;
    }

    @Override
    public Integer search() {
        List<Integer> list = getPrepared();
        for (int i = 0; i < getAmount(); i++) {
            list.indexOf(1);
        }
        return amount;
    }

    @Override
    public void prepareData() {
        prepared = null;
        prepared = getImplementationPrototype();
        for (int i = 0; i < prepared.size(); i++) {
            prepared.add(i);
        }
    }

    private List<Integer> getImplementationPrototype() {
        switch (type) {
            case ARRAY_LIST:
                return new ArrayList<>();
            case LINKED_LIST:
                return new LinkedList<>();
            case COW_ARRAY_LIST:
                return new CopyOnWriteArrayList<>();
            default:
                throw new RuntimeException("No such implementation");
        }
    }


    private List<Integer> getPrepared() {
        return prepared;
    }

    private Integer getAmount() {
        return amount;
    }

    private long getNumberOfOperation() {
        if (amount == -1) throw new RuntimeException("No amount to work with.");
        return amount;
    }
}
