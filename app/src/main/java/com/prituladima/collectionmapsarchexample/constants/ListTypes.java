package com.prituladima.collectionmapsarchexample.constants;

public enum ListTypes implements Types {
    ARRAY_LIST("ArrayList"),
    LINKED_LIST("LinkedList"),
    COW_ARRAY_LIST("CopyOnWriteArrayList");

    String implementation;

    ListTypes(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String get() {
        return implementation;
    }
}
