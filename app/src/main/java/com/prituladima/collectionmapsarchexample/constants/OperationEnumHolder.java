package com.prituladima.collectionmapsarchexample.constants;

public interface OperationEnumHolder {

    interface ListOperationEnumHolder {

        String LIST_NAME = "Collection";

        enum ListImplementation implements Implementation {
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

        enum ListOperation implements Operation {
            ADD_IN_THE_HEAD("Adding in the head"),
            ADD_IN_THE_TAIL("Adding in the tail"),
            ADD_IN_THE_MIDDLE("Adding in the middle"),
            REMOVE_FROM_HEAD("Removing from head"),
            REMOVE_FROM_TAIL("Removing from tail"),
            REMOVE_FROM_MIDDLE("Removing from middle"),
            SEARCH("Search by index");

            private String operation;

            ListOperation(String operation) {
                this.operation = operation;
            }

            @Override
            public String getOperation() {
                return operation;
            }

        }

    }

    interface MAP_OPERATIONS {

        interface IMPL {
            int HASHMAP = 1;
            int THREEMAP = 2;
        }

        interface OPERATIONS {
            int ADDING_WITH_RIGHT_HESH = 1;
            int SEARCH_WITH_RIGHT_HESH = 2;
            int REMOVE_WITH_RIGHT_HESH = 3;
        }

    }
}
