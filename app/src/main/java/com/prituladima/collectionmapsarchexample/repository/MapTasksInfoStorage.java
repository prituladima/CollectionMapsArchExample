package com.prituladima.collectionmapsarchexample.repository;

import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.constants.*;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;

import java.util.*;

public final class MapTasksInfoStorage implements TasksInfoStorage {

  private final List<TaskInfo> list;

  public MapTasksInfoStorage() {
    list = new ArrayList<>();
    int index = 0;
    for (MapTaskType operation : MapTaskType.values())
      for (MapDataType implementation : MapDataType.values())
        list.add(new TaskInfo(operation, implementation, index++));
  }

  @Override
  public synchronized List<TaskInfo> get() {
    return new ArrayList<>(list);
  }
}
