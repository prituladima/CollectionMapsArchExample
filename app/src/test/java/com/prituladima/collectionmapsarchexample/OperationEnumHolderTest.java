package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;

public class OperationEnumHolderTest {

    private static final int EXPECTED_AMOUNT_OF_OPERATION = 7;
    private static final int EXPECTED_AMOUNT_OF_IMPLEMENTATION = 3;


    @Test
    public void amountOfOperation(){
        assertEquals(EXPECTED_AMOUNT_OF_OPERATION, Operation.values().length);
    }

    @Test
    public void amountOfImplementation(){
        assertEquals(EXPECTED_AMOUNT_OF_IMPLEMENTATION, Implementation.values().length);
    }

}
