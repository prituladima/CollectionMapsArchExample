package com.prituladima.collectionmapsarchexample.arch.processor;


import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public final class CollectionTimeProcessor implements CollectionProcessor<Long>{

    private Implementation type;
    private Operation operation;
    private int amount = -1;

    public CollectionTimeProcessor(Implementation type, Operation operation, int amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public Long addInTheHead() {
        CollectionOperationProcessor processor = getProcessor();
        long start = getNow();
        processor.addInTheHead();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long addInTheTail() {
        CollectionOperationProcessor processor = getProcessor();
        long start = getNow();
        processor.addInTheTail();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long addInTheMiddle() {
        CollectionOperationProcessor processor = getProcessor();
        long start = getNow();
        processor.addInTheMiddle();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromTheHead() {
        CollectionOperationProcessor processor = getProcessor();
        getProcessor().prepareData();
        long start = getNow();
        processor.removeFromTheHead();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromTheTail() {
        CollectionOperationProcessor processor = getProcessor();
        getProcessor().prepareData();
        long start = getNow();
        processor.removeFromTheTail();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long removeFromMiddle() {
        CollectionOperationProcessor processor = getProcessor();
        getProcessor().prepareData();
        long start = getNow();
        processor.removeFromMiddle();
        long finish = getNow();
        return finish - start;
    }

    @Override
    public Long search() {
        CollectionOperationProcessor processor = getProcessor();
        getProcessor().prepareData();
        long start = getNow();
        processor.search();
        long finish = getNow();
        return finish - start;
    }

    private long getNow(){
        return System.currentTimeMillis();
    }

    private CollectionOperationProcessor getProcessor(){
        return new CollectionOperationProcessor(type, operation, amount);
    }
}
