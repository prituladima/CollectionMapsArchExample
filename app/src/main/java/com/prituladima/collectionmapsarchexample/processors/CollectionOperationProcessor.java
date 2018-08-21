package com.prituladima.collectionmapsarchexample.processors;

import com.prituladima.collectionmapsarchexample.constants.*;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.exceptions.*;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static java.lang.System.currentTimeMillis;

public final class CollectionOperationProcessor
    implements CollectionProcessor,
        DataTypeStorage<List<Integer>>,
        PreparedDataStorage<List<Integer>> {

  private final ListDataType implementation;
  private final ListTaskType operation;
  private final int amount;

  public CollectionOperationProcessor(TaskInfo holder, int amount) {
    this.implementation = (ListDataType) holder.getDataType();
    this.amount = amount;
    this.operation = (ListTaskType) holder.getTaskType();
  }

  @Override
  public long addInTheHead() {
    List<Integer> list = getNew();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.add(0, i);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long addInTheTail() {
    List<Integer> list = getNew();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.add(i);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long addInTheMiddle() {
    List<Integer> list = getNew();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.add(list.size() / 2, i);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long removeFromTheHead() {
    List<Integer> list = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.remove(0);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long removeFromTheTail() {
    List<Integer> list = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.remove(list.size() - 1);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long removeFromMiddle() {

    List<Integer> list = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.remove(list.size() / 2);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public long search() {
    List<Integer> list = getPrepared();
    long start = currentTimeMillis();
    for (int i = 0; i < amount; i++) {
      list.indexOf(1);
    }
    long finish = currentTimeMillis();
    return finish - start;
  }

  @Override
  public List<Integer> getPrepared() {
    List<Integer> prepared = getNew();
    for (int i = 0; i < amount; i++) {
      prepared.add(i);
    }
    return prepared;
  }

  @Override
  public List<Integer> getNew() {
    switch (implementation) {
      case ARRAY_LIST:
        return new ArrayList<>();
      case LINKED_LIST:
        return new LinkedList<>();
      case COW_ARRAY_LIST:
        return new CopyOnWriteArrayList<>();
      default:
        throw new NoSuchImplementationException();
    }
  }

  @Override
  public long execute() {
    switch (operation) {
      case ADD_IN_THE_HEAD:
        return addInTheHead();
      case ADD_IN_THE_MIDDLE:
        return addInTheMiddle();
      case ADD_IN_THE_TAIL:
        return addInTheTail();
      case REMOVE_FROM_HEAD:
        return removeFromTheHead();
      case REMOVE_FROM_MIDDLE:
        return removeFromMiddle();
      case REMOVE_FROM_TAIL:
        return removeFromTheTail();
      case SEARCH_BY_INDEX:
        return search();
      default:
        throw new NoSuchOperationException();
    }
  }
}
