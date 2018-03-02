package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.collection.CellRepositoryTest;
import com.prituladima.collectionmapsarchexample.collection.CollectionThreadPoolTest;
import com.prituladima.collectionmapsarchexample.collection.ListTaskTypeInfoStorageTest;
import com.prituladima.collectionmapsarchexample.collection.TaskEnumHolderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListTaskTypeInfoStorageTest.class,
        TaskEnumHolderTest.class,
        CellRepositoryTest.class,
        CollectionThreadPoolTest.class
})
public class JUnitTestSuiteRunner {
}

