package com.prituladima.collectionmapsarchexample.constants;

public final class TaskInfo {

    private final Operation operationType;
    private final Implementation implementation;
    private final int positionInStorage;

    public TaskInfo(Operation operationType, Implementation implementation, int positionInStorage) {
        this.operationType = operationType;
        this.implementation = implementation;
        this.positionInStorage = positionInStorage;
    }

    public Operation getOperationType() {
        return operationType;
    }

    public Implementation getImplementation() {
        return implementation;
    }

    public int getPositionInStorage() {
        return positionInStorage;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "operationType=" + operationType +
                ", implementation=" + implementation +
                ", positionInStorage=" + positionInStorage +
                '}';
    }
}
