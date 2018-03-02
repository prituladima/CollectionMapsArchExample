package com.prituladima.collectionmapsarchexample.constants;

public enum MapTaskType implements TaskType {
    ADDING_NEW("Adding new"),
    SEARCH_BY_KEY("Search by key"),
    REMOVING("Removing");

    private String operation;

    MapTaskType(String operation) {
        this.operation = operation;
    }

    @Override
    public String value() {
        return operation;
    }

}
