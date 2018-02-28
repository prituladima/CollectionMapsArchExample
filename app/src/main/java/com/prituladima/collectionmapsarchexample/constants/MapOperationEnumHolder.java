package com.prituladima.collectionmapsarchexample.constants;

/**
 * Created by prituladima on 3/1/18.
 */
public interface MapOperationEnumHolder {

    String MAP_NAME = "Map";

    enum MapImplementation implements Implementation {
        HASH_MAP("HashMap"),
        THREE_MAP("ThreeMap");

        String implementation;

        MapImplementation(String implementation) {
            this.implementation = implementation;
        }

        @Override
        public String getImplementation() {
            return implementation;
        }
    }

    enum MapOperation implements Operation {
        ADDING_NEW("Adding new"),
        SEARCH_BY_KEY("Search by key"),
        REMOVING("Removing");

        private String operation;

        MapOperation(String operation) {
            this.operation = operation;
        }

        @Override
        public String getOperation() {
            return operation;
        }

    }

}
