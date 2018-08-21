package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.entities.TaskInfo;

import java.util.List;

public interface TasksInfoStorage {

  List<TaskInfo> get();
}
