package com.prituladima.collectionmapsarchexample.arch.processor;

public interface CollectionProcessor<Result> extends Processor {

    Result addInTheHead();
    Result addInTheTail();
    Result addInTheMiddle();

    Result removeFromTheHead();
    Result removeFromTheTail();
    Result removeFromMiddle();

    Result search();

    Result execute();

}