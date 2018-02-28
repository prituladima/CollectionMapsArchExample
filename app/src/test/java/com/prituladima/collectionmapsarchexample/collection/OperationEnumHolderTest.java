package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.constants.ListImplementation;
import com.prituladima.collectionmapsarchexample.constants.ListOperation;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OperationEnumHolderTest {

    private static final int EXPECTED_AMOUNT_OF_OPERATION = 7;
    private static final int EXPECTED_AMOUNT_OF_IMPLEMENTATION = 3;


    @Test
    public void amountOfOperation(){
        assertEquals(EXPECTED_AMOUNT_OF_OPERATION, ListOperation.values().length);
    }

    @Test
    public void amountOfImplementation(){
        assertEquals(EXPECTED_AMOUNT_OF_IMPLEMENTATION, ListImplementation.values().length);
    }

}
