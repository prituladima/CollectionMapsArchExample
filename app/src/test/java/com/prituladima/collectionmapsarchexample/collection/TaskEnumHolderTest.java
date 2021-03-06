package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.constants.ListDataType;
import com.prituladima.collectionmapsarchexample.constants.ListTaskType;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TaskEnumHolderTest {

    private static final int EXPECTED_AMOUNT_OF_OPERATION = 7;
    private static final int EXPECTED_AMOUNT_OF_IMPLEMENTATION = 3;


    @Test
    public void amountOfOperation(){
        assertEquals(EXPECTED_AMOUNT_OF_OPERATION, ListTaskType.values().length);
    }

    @Test
    public void amountOfImplementation(){
        assertEquals(EXPECTED_AMOUNT_OF_IMPLEMENTATION, ListDataType.values().length);
    }

}
