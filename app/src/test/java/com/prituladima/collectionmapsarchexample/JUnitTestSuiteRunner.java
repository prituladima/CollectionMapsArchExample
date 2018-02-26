package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.collection.CollectionRepositoryTest;
import com.prituladima.collectionmapsarchexample.collection.CollectionThreadPoolTest;
import com.prituladima.collectionmapsarchexample.collection.OperationDataStorageTest;
import com.prituladima.collectionmapsarchexample.collection.OperationEnumHolderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        OperationDataStorageTest.class,
        OperationEnumHolderTest.class,
        CollectionRepositoryTest.class,
        CollectionThreadPoolTest.class
})
public class JUnitTestSuiteRunner {
}

