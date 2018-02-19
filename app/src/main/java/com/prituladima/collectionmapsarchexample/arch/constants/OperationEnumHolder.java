package com.prituladima.collectionmapsarchexample.arch.constants;

public interface OperationEnumHolder {

    interface ListOperationEnumHolder {

        enum Implementation {
            ARRAY_LIST(1),
            LINKED_LIST(2),
            COW_ARRAY_LIST(3);

            int value;

            Implementation(int value) {
                this.value = value;
            }
        }

        enum Operation {
            ADD_IN_THE_HEAD(1),
            ADD_IN_THE_TAIL(2),
            ADD_IN_THE_MIDDLE(3),
            REMOVE_FROM_HEAD(4),
            REMOVE_FROM_TAIL(5),
            REMOVE_FROM_MIDDLE(6),
            SEARCH(7);

            int value;

            Operation(int value) {
                this.value = value;
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
