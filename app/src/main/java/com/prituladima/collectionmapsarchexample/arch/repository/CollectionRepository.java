package com.prituladima.collectionmapsarchexample.arch.repository;

import com.prituladima.collectionmapsarchexample.arch.constants.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.entity.CellDTO;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;

public class CollectionRepository implements Repository {

    private PublishSubject<Boolean> subject;
    private OperationDataStorage storage;
    private List<CellDTO> data;

    public CollectionRepository(PublishSubject<Boolean> subject,
                                OperationDataStorage storage) {
        this.subject = subject;
        this.storage = storage;
        data = getDefault();
    }

    @Override
    public synchronized void put(int position, long time, boolean isLoading, boolean isLast) {
        data.set(position, new CellDTO(time, isLoading));
        subject.onNext(isLast);
    }

    @Override
    public List<CellDTO> get() {
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