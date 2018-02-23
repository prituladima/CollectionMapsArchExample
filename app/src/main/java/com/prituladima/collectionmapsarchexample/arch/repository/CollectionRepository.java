package com.prituladima.collectionmapsarchexample.arch.repository;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.ArrayList;
import java.util.List;

import rx.subjects.PublishSubject;

public class CollectionRepository implements Repository {

    private PublishSubject subject;
    private List<CellDTO> data;

    public CollectionRepository(PublishSubject subject) {
        this.subject = subject;
        data = getDefault();
    }

    @Override
    public synchronized void put(int position, long time, boolean isLoading) {
        data.set(position, new CellDTO(time, isLoading));
        subject.onNext(null);
    }

    @Override
    public List<CellDTO> get() {
        return new ArrayList<>(data);
    }

    @Override
    public List<CellDTO> getDefault() {
        List<CellDTO> list = new ArrayList<>();
        for (int i = 0; i < OperationDataStorage.getInstance().getList().size(); i++) {
            list.add(new CellDTO(0, false));
        }
        return list;
    }

    @Override
    public synchronized void reset() {
        data = getDefault();
    }

}