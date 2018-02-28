package com.prituladima.collectionmapsarchexample.constants;

import java.util.ArrayList;
import java.util.List;


public final class ListTasksInfoStorage implements TasksInfoStorage {

    private final List<TaskInfo> list;

    public ListTasksInfoStorage() {
        list = new ArrayList<>();
        int index = 0;
        for (ListTasks operation : ListTasks.values())
            for (ListTypes implementation : ListTypes.values())
                list.add(new TaskInfo(operation, implementation, index++));
    }

    @Override
    public synchronized List<TaskInfo> get() {
        return new ArrayList<>(list);
    }

}
