package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.Logger;
import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;
import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.processor.CollectionOperationProcessor;
import com.prituladima.collectionmapsarchexample.arch.processor.CollectionProcessor;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.concurrent.CountDownLatch;

public class OperationRunnable implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(OperationRunnable.class);
    private OperationParamHolder holder;
    private int amount;
    private Repository repository;
    private CountDownLatch countDownLatch;

    public OperationRunnable(OperationParamHolder holder, int amount, Repository repository, CountDownLatch countDownLatch) {
        this.holder = holder;
        this.amount = amount;
        this.repository = repository;
        this.countDownLatch = countDownLatch;
    }

    public OperationRunnable(OperationParamHolder holder, int amount, Repository repository) {
        this(holder, amount, repository, null);
    }

    @Override
    public void run() {
        Long time = 0L;
        try {
            repository.put(holder.getPositionInStorage(), time, true);
            CollectionProcessor<Long> processor = new CollectionOperationProcessor(holder.getImplementation(), holder.getOperationType(), amount);
            time = processor.execute();
            repository.put(holder.getPositionInStorage(), time, false);
        }catch (Throwable throwable){
            time = -1L;
            repository.put(holder.getPositionInStorage(), time, false);
            System.out.println(throwable);
        } finally {
            LOGGER.log(" --- " + new CellDTO(time, false)  + " --- " + holder);
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
        }
    }

}