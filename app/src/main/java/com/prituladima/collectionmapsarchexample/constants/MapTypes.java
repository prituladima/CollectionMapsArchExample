package com.prituladima.collectionmapsarchexample.constants;

public enum MapTypes implements Types {
    HASH_MAP("HashMap"),
    THREE_MAP("ThreeMap");

    String implementation;

    MapTypes(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String get() {
        return implementation;
    }
}
