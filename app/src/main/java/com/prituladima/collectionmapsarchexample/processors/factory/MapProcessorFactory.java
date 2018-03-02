package com.prituladima.collectionmapsarchexample.processors.factory;

import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.processors.MapOperationProcessor;
import com.prituladima.collectionmapsarchexample.processors.Processor;

public final class MapProcessorFactory implements ProcessorFactory {


    @Override
    public Processor getNewInstance(TaskInfo holder, int amount) {
        return new MapOperationProcessor(holder, amount);
    }
}
