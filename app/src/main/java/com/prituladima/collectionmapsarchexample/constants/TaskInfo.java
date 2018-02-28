package com.prituladima.collectionmapsarchexample.constants;

public final class TaskInfo {

    private final Tasks taskType;
    private final Types types;
    private final int positionInStorage;

    public TaskInfo(Tasks taskType, Types types, int positionInStorage) {
        this.taskType = taskType;
        this.types = types;
        this.positionInStorage = positionInStorage;
    }

    public Tasks getTaskType() {
        return taskType;
    }

    public Types getTypes() {
        return types;
    }

    public int getPositionInStorage() {
        return positionInStorage;
    }

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskType=" + taskType +
                ", types=" + types +
                ", positionInStorage=" + positionInStorage +
                '}';
    }
}
