package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.constants.ListTasksInfoStarage;
import com.prituladima.collectionmapsarchexample.constants.TasksInfoStarage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ListTasksInfoStarageTest {

    private static final int EXPECTED_SIZE = 21;
    private TasksInfoStarage storage;

    @Before
    public void init(){
        storage = new ListTasksInfoStarage();
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