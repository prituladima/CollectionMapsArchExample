package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.entity.CellDTO;
import com.prituladima.collectionmapsarchexample.entity.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.processor.Processor;
import com.prituladima.collectionmapsarchexample.util.Logger;

import java.util.concurrent.CountDownLatch;

public final class OperationRunnable implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(OperationRunnable.class);
    private OperationParamHolder holder;
    private int amount;
    private Repository repository;
    private CountDownLatch countDownLatch;
    private Processor processor;

    public OperationRunnable(Processor processor, OperationParamHolder holder, int amount, Repository repository, CountDownLatch countDownLatch) {
        this.holder = holder;
        this.amount = amount;
        this.repository = repository;
        this.countDownLatch = countDownLatch;
        this.processor = processor;
    }

    @Override
    public void run() {
        Long time = 0L;
        try {
            repository.put(holder.getPositionInStorage(), time, true, false);
            time = processor.execute();
            repository.put(holder.getPositionInStorage(), time, false, false);
        } catch (Throwable throwable) {
            time = -1L;
            repository.put(holder.getPositionInStorage(), time, false, false);
            System.out.println(throwable);
        } finally {
            LOGGER.log(" --- " + new CellDTO(time, false) + " --- " + holder);
            countDownLatch.countDown();
            if (countDownLatch.getCount() == 0L) {
                repository.put(holder.getPositionInStorage(), time, false, true);
            }
        }
    }

}