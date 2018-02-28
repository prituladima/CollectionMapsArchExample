package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.constants.OperationDataStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ListOperationDataStorageTest {

    private static final int EXPECTED_SIZE = 21;
    private OperationDataStorage storage;

    @Before
    public void init(){
        storage = new ListOperationDataStorage();
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