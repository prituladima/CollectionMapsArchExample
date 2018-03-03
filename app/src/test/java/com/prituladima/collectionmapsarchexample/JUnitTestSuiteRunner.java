package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.collection.CellRepositoryTest;
import com.prituladima.collectionmapsarchexample.collection.CollectionThreadPoolTest;
import com.prituladima.collectionmapsarchexample.collection.ListTaskTypeInfoStorageTest;
import com.prituladima.collectionmapsarchexample.collection.TaskEnumHolderTest;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresentersTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListTaskTypeInfoStorageTest.class,
        TaskEnumHolderTest.class,
        CellRepositoryTest.class,
        CollectionThreadPoolTest.class,
        CollectionPresentersTest.class
})
public class JUnitTestSuiteRunner {
}

