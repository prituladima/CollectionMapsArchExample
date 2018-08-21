package com.prituladima.collectionmapsarchexample.processors;

import com.prituladima.collectionmapsarchexample.constants.*;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.exceptions.*;

import java.util.*;

import static java.lang.System.currentTimeMillis;

public final class MapOperationProcessor
    implements MapProcessor,
        DataTypeStorage<Map<String, String>>,
        PreparedDataStorage<Map<String, String>> {

  private final MapDataType implementation;
  private final MapTaskType operation;
  private final int amount;

  public MapOperationProcessor(TaskInfo holder, int amount) {
    this.implementation = (MapDataType) holder.getDataType();
    this.amount = amount;
    this.operation = (MapTaskType) holder.getTaskType();
  }

  @Override
  public long execute() {
    switch (operation) {
      case ADDING_NEW:
        return add();
      case SEARCH_BY_KEY:
        return search();
      case REMOVING:
        return remove();
      default:
        throw new NoSuchOperationException();
    }
  }

  @Override
  public Map<String, String> getNew() {
    switch (implementation) {
      case HASH_MAP:
        return new HashMap<>();
      case THREE_MAP:
        return new TreeMap<>();
      default:
        throw new NoSuchImplementationException();
    }
  }

  @Override
  public Map<String, String> getPrepared() {
    Map<String, String> prepared = getNew();
    for (int i = 0; i < amount; i++) {
      prepared.put(String.valueOf(i), String.valueOf(i));
    }
    return prepared;
  }

  @Override
  public long add() {
    Map<String, String> map = getNew();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      map.put(String.valueOf(i), String.valueOf(i));
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long search() {
    Map<String, String> map = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      map.get(String.valueOf(i));
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long remove() {
    Map<String, String> map = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      map.remove(String.valueOf(i));
    }
    long finish = currentTimeMillis();
    return finish - start;
  }
}
