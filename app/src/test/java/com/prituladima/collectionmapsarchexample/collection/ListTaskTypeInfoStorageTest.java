package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ListTaskTypeInfoStorageTest {

    private static final int EXPECTED_SIZE = 21;
    private TasksInfoStorage storage;

    @Before
    public void init(){
        storage = new ListTasksInfoStorage();
    }

    @Test
    public void amountOfRow() {
        assertEquals(EXPECTED_SIZE, storage.get().size());
    }

    @Test
    public void immutabilityTest(){
        List list = storage.get();
        list.clear();
        assertEquals(EXPECTED_SIZE, storage.get().size());
    }


}