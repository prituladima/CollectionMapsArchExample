package com.prituladima.collectionmapsarchexample.arch.processor;

import com.prituladima.collectionmapsarchexample.arch.exceptions.NoSuchImplementationException;
import com.prituladima.collectionmapsarchexample.arch.exceptions.NoSuchOperationException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class CollectionOperationProcessor implements
        CollectionProcessor<Long>,
        ImplementationProvider<List<Integer>>,
        PreparedDataProvider<List<Integer>> {


    private Implementation type;
    private Operation operation;
    private int amount = -1;

    public CollectionOperationProcessor(Implementation type, Operation operation, int amount) {
        this.type = type;
        this.amount = amount;
        this.operation = operation;
    }

    @Override
    public Long addInTheHead() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(0, i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long addInTheTail() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long addInTheMiddle() {
        List<Integer> list = getImplementationPrototype();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.add(list.size() / 2, i);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromTheHead() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(0);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromTheTail() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() - 1);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromMiddle() {

        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.remove(list.size() / 2);
        }
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long search() {
        List<Integer> list = getPreparedData();
        long start = getNow();
        for (int i = 0; i < amount; i++) {
            list.indexOf(1);
        }
        long finish = getNow();
        return finish - start;
    }



    ///////////TODO move to another implementation

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

    public Long execute(){
        switch (operation){
            case ADD_IN_THE_HEAD: return addInTheHead();
            case ADD_IN_THE_MIDDLE: return addInTheMiddle();
            case ADD_IN_THE_TAIL: return addInTheTail();

            case REMOVE_FROM_HEAD: return removeFromTheHead();
            case REMOVE_FROM_MIDDLE: return removeFromMiddle();
            case REMOVE_FROM_TAIL: return removeFromTheTail();
            case SEARCH: return search();
            default: throw new NoSuchOperationException();
        }
    }

    private long getNow(){
        return System.currentTimeMillis();
    }

}