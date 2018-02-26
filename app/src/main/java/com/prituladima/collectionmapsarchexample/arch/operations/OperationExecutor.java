package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OperationExecutor {

    private ExecutorService executorService;
    private List<Runnable> runnables;
    private CountDownLatch latch;
    private boolean isStarted = false;

    private OperationExecutor(ExecutorService executorService, List<Runnable> runnables, CountDownLatch latch) {
        this.executorService = executorService;
        this.runnables = runnables;
        this.latch = latch;
    }

    public void start() {
        if(isStarted) throw new ProcessorIsStillRunningException();
        isStarted = true;
        for (Runnable runnable : runnables) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }

    public void stop() {
        int rest = executorService.shutdownNow().size();
        for (int i = 0; i < rest; i++) {
            latch.countDown();
        }
    }

    public boolean isRunning(){
        return latch.getCount() != 0L;
    }

    public static class OperationExecutorBuilder {

        private CountDownLatch latch;
        private int threads;
        private int amount;
        private Repository repository;
        private OperationDataStorage storage;

        public OperationExecutorBuilder(CountDownLatch latch,
                                        int threads,
                                        int amount,
                                        Repository repository,
                                        OperationDataStorage storage) {
            this.latch = latch;
            this.threads = threads;
            this.amount = amount;
            this.repository = repository;
            this.storage = storage;
        }

        public OperationExecutor build() {
            ExecutorService executorService = Executors.newFixedThreadPool(threads);
            List<Runnable> runnableList = new ArrayList<>();
            for (OperationParamHolder holder : storage.getList()) {
                runnableList.add(new OperationRunnable(holder, amount, repository, latch));
            }

            return new OperationExecutor(executorService, runnableList, latch);
        }
    }

}