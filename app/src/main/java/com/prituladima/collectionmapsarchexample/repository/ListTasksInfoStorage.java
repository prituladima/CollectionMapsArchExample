package com.prituladima.collectionmapsarchexample.repository;

import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.constants.ListDataType;
import com.prituladima.collectionmapsarchexample.constants.ListTaskType;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;

import java.util.ArrayList;
import java.util.List;


public final class ListTasksInfoStorage implements TasksInfoStorage {

    private final List<TaskInfo> list;

    public ListTasksInfoStorage() {
        list = new ArrayList<>();
        int index = 0;
        for (ListTaskType operation : ListTaskType.values())
            for (ListDataType implementation : ListDataType.values())
                list.add(new TaskInfo(operation, implementation, index++));
    }

    @Override
    public synchronized List<TaskInfo> get() {
        return new ArrayList<>(list);
    }

}
