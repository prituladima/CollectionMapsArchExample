package com.prituladima.collectionmapsarchexample.arch.entity;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class OperationParamHolder {

    private ListOperation operationType;
    private ListImplementation implementation;
    private int positionInStorage;

    public OperationParamHolder(ListOperation operationType, ListImplementation implementation, int positionInStorage) {
        this.operationType = operationType;
        this.implementation = implementation;
        this.positionInStorage = positionInStorage;
    }

    public ListOperation getOperationType() {
        return operationType;
    }

    public ListImplementation getImplementation() {
        return implementation;
    }

    public int getPositionInStorage() {
        return positionInStorage;
    }

    @Override
    public String toString() {
        return "OperationParamHolder{" +
                "operationType=" + operationType +
                ", implementation=" + implementation +
                ", positionInStorage=" + positionInStorage +
                '}';
    }
}
