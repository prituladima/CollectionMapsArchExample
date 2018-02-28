package com.prituladima.collectionmapsarchexample.constants;

public enum ListTasks implements Tasks {
    ADD_IN_THE_HEAD("Adding in the head"),
    ADD_IN_THE_TAIL("Adding in the tail"),
    ADD_IN_THE_MIDDLE("Adding in the middle"),
    REMOVE_FROM_HEAD("Removing from head"),
    REMOVE_FROM_TAIL("Removing from tail"),
    REMOVE_FROM_MIDDLE("Removing from middle"),
    SEARCH("Search by index");

    private String operation;

    ListTasks(String operation) {
        this.operation = operation;
    }

    @Override
    public String get() {
        return operation;
    }

}
