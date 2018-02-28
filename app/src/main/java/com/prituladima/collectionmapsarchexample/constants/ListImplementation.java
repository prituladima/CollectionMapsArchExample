package com.prituladima.collectionmapsarchexample.constants;

/**
 * Created by prituladima on 3/1/18.
 */
public enum ListImplementation implements Implementation {
    ARRAY_LIST("ArrayList"),
    LINKED_LIST("LinkedList"),
    COW_ARRAY_LIST("CopyOnWriteArrayList");

    String implementation;

    ListImplementation(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String getImplementation() {
        return implementation;
    }
}
