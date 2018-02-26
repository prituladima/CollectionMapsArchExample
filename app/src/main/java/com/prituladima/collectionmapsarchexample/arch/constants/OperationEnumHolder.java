package com.prituladima.collectionmapsarchexample.arch.constants;

public interface OperationEnumHolder {

    interface ListOperationEnumHolder {

        String LIST_NAME = "Collection";

        enum Implementation {
            ARRAY_LIST(1, "ArrayList"),
            LINKED_LIST(2, "LinkedList"),
            COW_ARRAY_LIST(3, "CopyOnWriteArrayList");

            int value;
            String name;

            Implementation(int value, String name) {
                this.value = value;
                this.name = name;
            }

            public int getValue() {
                return value;
            }

            public String getName() {
                return name;
            }
        }

        enum Operation {
            ADD_IN_THE_HEAD(1, "Adding in the head"),
            ADD_IN_THE_TAIL(2, "Adding in the tail"),
            ADD_IN_THE_MIDDLE(3, "Adding in the middle"),
            REMOVE_FROM_HEAD(4, "Removing from head"),
            REMOVE_FROM_TAIL(5, "Removing from tail"),
            REMOVE_FROM_MIDDLE(6, "Removing from middle"),
            SEARCH(7, "Search by index");

            int value;
            String description;

            Operation(int value, String description) {
                this.value = value;
                this.description = description;
            }

            public int getValue() {
                return value;
            }

            public void setValue(int value) {
                this.value = value;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
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
