package com.prituladima.collectionmapsarchexample.arch.processor;

/**
 * Created by prituladima on 2/18/18.
 */

public interface CollectionProcessor<Result> extends Processor {

    Result addInTheHead();
    Result addInTheTail();
    Result addInTheMiddle();

    Result removeFromTheHead();
    Result removeFromTheTail();
    Result removeFromMiddle();

    Result search();


}
