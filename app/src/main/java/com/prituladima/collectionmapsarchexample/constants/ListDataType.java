package com.prituladima.collectionmapsarchexample.constants;

public enum ListDataType implements DataType {

    ARRAY_LIST("ArrayList"),
    LINKED_LIST("LinkedList"),
    COW_ARRAY_LIST("CopyOnWriteArrayList");

    String value;

    ListDataType(String value) {
        this.value = value;
    }

    @Override
    public String get() {
        return value;
    }
}
