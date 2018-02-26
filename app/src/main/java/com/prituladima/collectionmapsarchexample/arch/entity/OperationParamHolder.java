package com.prituladima.collectionmapsarchexample.arch.entity;

import com.prituladima.collectionmapsarchexample.arch.constants.Implementation;
import com.prituladima.collectionmapsarchexample.arch.constants.Operation;

public class OperationParamHolder {

    private Operation operationType;
    private Implementation implementation;
    private int positionInStorage;

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
