package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.collection.CollectionRepositoryTest;
import com.prituladima.collectionmapsarchexample.collection.CollectionThreadPoolTest;
import com.prituladima.collectionmapsarchexample.collection.ListTasksInfoStorageTest;
import com.prituladima.collectionmapsarchexample.collection.TaskEnumHolderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListTasksInfoStorageTest.class,
        TaskEnumHolderTest.class,
        CollectionRepositoryTest.class,
        CollectionThreadPoolTest.class
})
public class JUnitTestSuiteRunner {
}

