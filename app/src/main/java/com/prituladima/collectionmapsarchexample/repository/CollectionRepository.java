package com.prituladima.collectionmapsarchexample.repository;

import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.constants.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entity.CellDTO;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;

public class CollectionRepository implements Repository {

    private final PublishSubject<Boolean> subject;
    private final TasksInfoStorage storage;

    private List<CellDTO> data;

    public CollectionRepository(PublishSubject<Boolean> subject,
                                TasksInfoStorage storage) {
        this.subject = subject;
        this.storage = storage;
        reset();
    }

    @Override
    public synchronized void put(int position, long time, boolean isLoading, boolean isLast) {
        data.set(position, new CellDTO(time, isLoading));
        subject.onNext(isLast);
    }

    @Override
    public synchronized List<CellDTO> get() {
        return new ArrayList<>(data);
    }

    @Override
    public List<CellDTO> getDefault() {
        List<CellDTO> list = new ArrayList<>();
        for (int i = 0; i < storage.get().size(); i++) {
            list.add(new CellDTO(0, false));
        }
        return list;
    }

    @Override
    public synchronized void reset() {
        data = getDefault();
    }

}