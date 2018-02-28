package com.prituladima.collectionmapsarchexample.constants;

/**
 * Created by prituladima on 3/1/18.
 */
public enum ListOperation implements Operation {
    ADD_IN_THE_HEAD("Adding in the head"),
    ADD_IN_THE_TAIL("Adding in the tail"),
    ADD_IN_THE_MIDDLE("Adding in the middle"),
    REMOVE_FROM_HEAD("Removing from head"),
    REMOVE_FROM_TAIL("Removing from tail"),
    REMOVE_FROM_MIDDLE("Removing from middle"),
    SEARCH("Search by index");

    private String operation;

    ListOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public String getOperation() {
        return operation;
    }

}
