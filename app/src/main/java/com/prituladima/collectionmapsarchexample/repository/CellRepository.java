package com.prituladima.collectionmapsarchexample.repository;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.Cell;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;

public final class CellRepository implements Repository {

    private final PublishSubject<Boolean> subject;
    private final TasksInfoStorage storage;

    private List<Cell> data;

    public CellRepository(PublishSubject<Boolean> subject,
                          TasksInfoStorage storage) {
        this.subject = subject;
        this.storage = storage;
        reset();
    }

    @Override
    public synchronized void put(int position, long time, boolean isLoading, boolean isLast) {
        data.set(position, new Cell(time, isLoading));
        subject.onNext(isLast);
    }

    @Override
    public synchronized List<Cell> get() {
        return new ArrayList<>(data);
    }


    private List<Cell> getDefault() {
        List<Cell> list = new ArrayList<>();
        for (int i = 0; i < storage.get().size(); i++) {
            list.add(new Cell(0, false));
        }
        return list;
    }

    @Override
    public synchronized void reset() {
        data = getDefault();
    }

}