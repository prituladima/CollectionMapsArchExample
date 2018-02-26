package com.prituladima.collectionmapsarchexample.collection;


import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

public class CollectionRepositoryTest {

    private static final int EXPECTED_SIZE = 21;

    private Repository repository;
    private PublishSubject publishSubject;
    private ListOperationDataStorage storage;

    @Before
    public void init(){
        publishSubject = PublishSubject.create();
        storage  = new ListOperationDataStorage();
        repository = new CollectionRepository(publishSubject, storage);
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
