package com.prituladima.collectionmapsarchexample.constants;

import java.util.ArrayList;
import java.util.List;


public final class ListTasksInfoStarage implements TasksInfoStarage {

    private final List<TaskInfo> list;

    public ListTasksInfoStarage() {
        list = new ArrayList<>();
        int index = 0;
        for (ListOperation operation : ListOperation.values())
            for (ListImplementation implementation : ListImplementation.values())
                list.add(new TaskInfo(operation, implementation, index++));
    }

    @Override
    public synchronized List<TaskInfo> get() {
        return new ArrayList<>(list);
    }

}
