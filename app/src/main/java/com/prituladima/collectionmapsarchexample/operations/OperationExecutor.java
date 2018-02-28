package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.constants.TaskInfo;
import com.prituladima.collectionmapsarchexample.constants.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.processor.Processor;
import com.prituladima.collectionmapsarchexample.processors.CollectionOperationProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class OperationExecutor implements LifecycleExecutor {

    private final ExecutorService executorService;
    private final List<Runnable> runnableList;
    private final CountDownLatch countDownLatch;

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

    public static final class OperationExecutorBuilder {

        private final CountDownLatch latch;
        private final int threads;
        private final int amount;
        private final Repository repository;
        private final TasksInfoStorage storage;

        public OperationExecutorBuilder(CountDownLatch latch,
                                        int threads,
                                        int amount,
                                        Repository repository,
                                        TasksInfoStorage storage) {
            this.latch = latch;
            this.threads = threads;
            this.amount = amount;
            this.repository = repository;
            this.storage = storage;
        }

        public OperationExecutor build() {
            ExecutorService executorService = Executors.newFixedThreadPool(threads);
            List<Runnable> runnableList = new ArrayList<>();
            for (TaskInfo holder : storage.get()) {
                Processor processor = new CollectionOperationProcessor(holder, amount);
                runnableList.add(new OperationRunnable(processor, holder, amount, repository, latch));
            }

            return new OperationExecutor(executorService, runnableList, latch);
        }
    }

}