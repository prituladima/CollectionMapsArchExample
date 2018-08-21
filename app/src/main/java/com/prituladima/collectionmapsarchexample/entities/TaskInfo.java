package com.prituladima.collectionmapsarchexample.entities;

import com.prituladima.collectionmapsarchexample.constants.*;

public final class TaskInfo {

  private final TaskType taskType;
  private final DataType dataType;
  private final int positionInStorage;

  public TaskInfo(TaskType taskType, DataType dataType, int positionInStorage) {
    this.taskType = taskType;
    this.dataType = dataType;
    this.positionInStorage = positionInStorage;
  }

  public TaskType getTaskType() {
    return taskType;
  }

  public DataType getDataType() {
    return dataType;
  }

  public int getPositionInStorage() {
    return positionInStorage;
  }

  @Override
  public String toString() {
    return "TaskInfo{"
        + "taskType="
        + taskType
        + ", dataType="
        + dataType
        + ", positionInStorage="
        + positionInStorage
        + '}';
  }
}
