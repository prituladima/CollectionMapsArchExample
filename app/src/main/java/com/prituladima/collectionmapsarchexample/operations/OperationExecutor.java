package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.arch.*;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.processors.Processor;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;

import java.util.*;
import java.util.concurrent.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

public final class OperationExecutor implements LifecycleExecutor {

  private final ExecutorService executorService;
  private final List<Runnable> runnableList;
  private final CountDownLatch countDownLatch;

  private boolean isStarted = false;

  private OperationExecutor(
      ExecutorService executorService, List<Runnable> runnableList, CountDownLatch countDownLatch) {
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
    private final ProcessorFactory factory;

    public OperationExecutorBuilder(
        CountDownLatch latch,
        int threads,
        int amount,
        Repository repository,
        TasksInfoStorage storage,
        ProcessorFactory factory) {
      if (latch == null) throw new IllegalArgumentException("Must not be null");
      this.latch = latch;

      this.threads = threads;

      if (amount <= 0) throw new IllegalArgumentException("Must not be < 0!!!");
      this.amount = amount;

      if (repository == null) throw new IllegalArgumentException("Must not be null");
      this.repository = repository;

      if (storage == null) throw new IllegalArgumentException("Must not be null");
      this.storage = storage;

      if (factory == null) throw new IllegalArgumentException("Must not be null");
      this.factory = factory;
    }

    public OperationExecutor build() {
      ExecutorService executorService = newFixedThreadPool(threads);
      List<Runnable> runnableList = new ArrayList<>();
      for (TaskInfo holder : storage.get()) {
        Processor processor = factory.getNewInstance(holder, amount);
        runnableList.add(new OperationRunnable(processor, holder, repository, latch));
      }

      return new OperationExecutor(executorService, runnableList, latch);
    }
  }
}
