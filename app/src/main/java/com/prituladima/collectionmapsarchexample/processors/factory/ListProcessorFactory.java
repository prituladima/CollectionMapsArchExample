package com.prituladima.collectionmapsarchexample.processors.factory;

import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.processors.*;

public final class ListProcessorFactory implements ProcessorFactory {

  @Override
  public Processor getNewInstance(TaskInfo holder, int amount) {
    return new CollectionOperationProcessor(holder, amount);
  }
}
