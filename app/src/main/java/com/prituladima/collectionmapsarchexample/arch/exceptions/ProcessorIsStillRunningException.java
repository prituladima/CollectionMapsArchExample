package com.prituladima.collectionmapsarchexample.arch.exceptions;

public class ProcessorIsStillRunningException extends RuntimeException {

    public ProcessorIsStillRunningException() {
        super("Processor is still running!!! You need shot it down first");
    }
}
