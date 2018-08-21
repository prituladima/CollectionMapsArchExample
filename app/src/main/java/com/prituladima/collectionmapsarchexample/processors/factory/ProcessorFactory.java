package com.prituladima.collectionmapsarchexample.processors.factory;

import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.processors.Processor;

public interface ProcessorFactory {

  Processor getNewInstance(TaskInfo holder, int amount);
}
