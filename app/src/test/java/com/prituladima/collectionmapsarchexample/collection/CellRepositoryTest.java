package com.prituladima.collectionmapsarchexample.collection;


import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

public class CellRepositoryTest {

    private static final int EXPECTED_SIZE = 21;

    private Repository repository;
    private PublishSubject publishSubject;
    private TasksInfoStorage storage;

    @Before
    public void init(){
        publishSubject = PublishSubject.create();
        storage  = new ListTasksInfoStorage();
        repository = new CellRepository(publishSubject, storage);
    }

    @Test
    public void amountOfRow() {
        assertEquals(EXPECTED_SIZE, repository.get().size());
    }

    @Test
    public void immutabilityTest(){
        List list = repository.get();
        list.clear();
        assertEquals(EXPECTED_SIZE, repository.get().size());
    }

}
