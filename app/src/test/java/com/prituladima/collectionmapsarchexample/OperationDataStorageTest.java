package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class OperationDataStorageTest {

    private static final int EXPECTED_SIZE = 21;
    private OperationDataStorage storage;

    @Before
    public void init(){
        storage = OperationDataStorage.getInstance();
    }

    @Test
    public void amountOfRow() {
        assertEquals(EXPECTED_SIZE, storage.getList().size());
    }

    @Test
    public void immutabilityTest(){
        List list = storage.getList();
        list.clear();
        assertEquals(EXPECTED_SIZE, storage.getList().size());
    }


}