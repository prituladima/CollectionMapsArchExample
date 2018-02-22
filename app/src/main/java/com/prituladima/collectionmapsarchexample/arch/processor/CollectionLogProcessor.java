package com.prituladima.collectionmapsarchexample.arch.processor;


import com.prituladima.collectionmapsarchexample.Logger;
import com.prituladima.collectionmapsarchexample.arch.exceptions.NoSuchOperationException;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationRunnable;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by prituladima on 2/18/18.
 */
import rx.Observable;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class CollectionLogProcessor implements CollectionProcessor<Long> {

    Logger LOGGER = Logger.getLogger(CollectionLogProcessor.class);


    CollectionTimeProcessor processor;
    Operation operation;


    private CollectionLogProcessor(Implementation type, Operation operation, int amount) {
        this.processor = new CollectionTimeProcessor(type, operation, amount);
        this.operation = operation;
    }

    public static CollectionLogProcessor getPrototype(Implementation type, Operation operation, int amount) {
        return new CollectionLogProcessor(type, operation, amount);
    }

    @Override
    public Long addInTheHead() {
        LOGGER.log("Adding in the head... ");
        return processor.addInTheHead();
    }

    @Override
    public Long addInTheTail() {
        LOGGER.log("Adding in the tail... ");
        return processor.addInTheTail();
    }

    @Override
    public Long addInTheMiddle() {
        LOGGER.log("Adding in the middle... ");
        return processor.addInTheMiddle();
    }

    @Override
    public Long removeFromTheHead() {
        LOGGER.log("Remove from the head... ");
        return processor.removeFromTheHead();
    }

    @Override
    public Long removeFromTheTail() {
        LOGGER.log("Remove from the tail... ");
        return processor.removeFromMiddle();
    }

    @Override
    public Long removeFromMiddle() {
        LOGGER.log("Remove from the middle... ");
        return processor.removeFromMiddle();
    }

    @Override
    public Long search() {
        LOGGER.log("Search by item... ");
        return processor.search();
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

    public static void main(String[] args) {

        int amount = 100_000;

        ExecutorService service = Executors.newFixedThreadPool(4);

        Observable.from(OperationDataStorage.getInstance().getList())
                .subscribe(x -> service.submit(new OperationRunnable(x, amount)));

        service.shutdown();

    }

}