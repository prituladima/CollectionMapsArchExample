package com.prituladima.collectionmapsarchexample.arch.operations;

import com.prituladima.collectionmapsarchexample.arch.entity.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.processor.Processor;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;
import com.prituladima.collectionmapsarchexample.impl.processors.CollectionOperationProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class OperationExecutor implements ExecutorLifecycle{

    private ExecutorService executorService;
    private List<Runnable> runnableList;
    private CountDownLatch countDownLatch;
    private boolean isStarted = false;

    private OperationExecutor(ExecutorService executorService, List<Runnable> runnableList, CountDownLatch countDownLatch) {
        this.executorService = executorService;
        this.runnableList = runnableList;
        this.countDownLatch = countDownLatch;
    }

    public void start() {
        if (isStarted) throw new ProcessorIsStillRunningException();
        isStarted = true;
        for (Runnable runnable : runnableList) {
            executorService.submit(runnable);
        }
        executorService.shutdown();
    }

    public void stop() {
        int rest = executorService.shutdownNow().size();
        for (int i = 0; i < rest; i++) {
            countDownLatch.countDown();
        }
    }

    public boolean isRunning() {
        return countDownLatch.getCount() != 0L;
    }

    public static class OperationExecutorBuilder {

        private CountDownLatch latch;
        private int threads;
        private int amount;
        private Repository repository;
        private ListOperationDataStorage storage;

        public OperationExecutorBuilder(CountDownLatch latch,
                                        int threads,
                                        int amount,
                                        Repository repository,
                                        ListOperationDataStorage storage) {
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
                Processor processor = new CollectionOperationProcessor(holder, amount);
                runnableList.add(new OperationRunnable(processor, holder, amount, repository, latch));
            }

            return new OperationExecutor(executorService, runnableList, latch);
        }
    }

}