package com.prituladima.collectionmapsarchexample.arch.processor;

public interface CollectionProcessor extends Processor {

    long addInTheHead();
    long addInTheTail();
    long addInTheMiddle();

    long removeFromTheHead();
    long removeFromTheTail();
    long removeFromMiddle();

    long search();

}