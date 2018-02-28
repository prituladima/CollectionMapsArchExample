package com.prituladima.collectionmapsarchexample.constants;

public enum MapTasks implements Tasks {
    ADDING_NEW("Adding new"),
    SEARCH_BY_KEY("Search by key"),
    REMOVING("Removing");

    private String operation;

    MapTasks(String operation) {
        this.operation = operation;
    }

    @Override
    public String get() {
        return operation;
    }

}
