package com.prituladima.collectionmapsarchexample;


import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;
import com.prituladima.collectionmapsarchexample.dagger.ContextModule;
import com.prituladima.collectionmapsarchexample.dagger.DaggerInjector;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;

public class CollectionRepositoryTest {

    private static final int EXPECTED_SIZE = 21;

    private Repository repository;
    private PublishSubject publishSubject;

    @Before
    public void init(){
        publishSubject = PublishSubject.create();
        repository = new CollectionRepository(publishSubject);
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
