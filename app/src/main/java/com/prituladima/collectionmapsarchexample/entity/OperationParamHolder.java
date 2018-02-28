package com.prituladima.collectionmapsarchexample.entity;

import com.prituladima.collectionmapsarchexample.constants.Implementation;
import com.prituladima.collectionmapsarchexample.constants.Operation;

public final class OperationParamHolder {

    private final Operation operationType;
    private final Implementation implementation;
    private final int positionInStorage;

    public OperationParamHolder(Operation operationType, Implementation implementation, int positionInStorage) {
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
        return "OperationParamHolder{" +
                "operationType=" + operationType +
                ", implementation=" + implementation +
                ", positionInStorage=" + positionInStorage +
                '}';
    }
}
