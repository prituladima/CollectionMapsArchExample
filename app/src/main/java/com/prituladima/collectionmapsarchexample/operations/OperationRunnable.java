package com.prituladima.collectionmapsarchexample.operations;

import com.prituladima.collectionmapsarchexample.Logger;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.entities.*;
import com.prituladima.collectionmapsarchexample.processors.Processor;

import java.util.concurrent.CountDownLatch;

public final class OperationRunnable implements Runnable {

  private static final Logger LOGGER = Logger.getLogger(OperationRunnable.class);

  private final TaskInfo info;
  private final int position;
  private final Repository repository;
  private final CountDownLatch countDownLatch;
  private final Processor processor;

  public OperationRunnable(
      Processor processor, TaskInfo info, Repository repository, CountDownLatch countDownLatch) {
    this.info = info;
    this.position = info.getPositionInStorage();
    this.repository = repository;
    this.countDownLatch = countDownLatch;
    this.processor = processor;
  }

  @Override
  public void run() {
    long time = 0L;
    try {
      repository.put(position, time, true, false);
      time = processor.execute();
      repository.put(position, time, false, false);
    } catch (Throwable throwable) {
      time = -1L;
      repository.put(position, time, false, false);
      LOGGER.error(throwable);
    } finally {
      LOGGER.log(" --- " + new Cell(time, false) + " --- " + info);
      countDownLatch.countDown();
      if (countDownLatch.getCount() == 0L) {
        repository.put(position, time, false, true);
      }
    }
  }
}
