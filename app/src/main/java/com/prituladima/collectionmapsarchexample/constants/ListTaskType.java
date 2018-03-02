package com.prituladima.collectionmapsarchexample.constants;

public enum ListTaskType implements TaskType {

    ADD_IN_THE_HEAD("Adding in the head"),
    ADD_IN_THE_TAIL("Adding in the tail"),
    ADD_IN_THE_MIDDLE("Adding in the middle"),
    REMOVE_FROM_HEAD("Removing from head"),
    REMOVE_FROM_TAIL("Removing from tail"),
    REMOVE_FROM_MIDDLE("Removing from middle"),
    SEARCH_BY_INDEX("Search by index");

    private String value;

    ListTaskType(String value) {
        this.value = value;
    }

    @Override
    public String value() {
        return value;
    }

}