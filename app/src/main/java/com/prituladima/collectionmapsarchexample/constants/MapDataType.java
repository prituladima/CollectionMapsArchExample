package com.prituladima.collectionmapsarchexample.constants;

public enum MapDataType implements DataType {
    HASH_MAP("HashMap"),
    THREE_MAP("ThreeMap");

    String implementation;

    MapDataType(String implementation) {
        this.implementation = implementation;
    }

    @Override
    public String get() {
        return implementation;
    }
}
