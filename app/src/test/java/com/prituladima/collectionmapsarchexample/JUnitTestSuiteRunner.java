package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.collection.CollectionRepositoryTest;
import com.prituladima.collectionmapsarchexample.collection.CollectionThreadPoolTest;
import com.prituladima.collectionmapsarchexample.collection.ListTasksInfoStarageTest;
import com.prituladima.collectionmapsarchexample.collection.OperationEnumHolderTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ListTasksInfoStarageTest.class,
        OperationEnumHolderTest.class,
        CollectionRepositoryTest.class,
        CollectionThreadPoolTest.class
})
public class JUnitTestSuiteRunner {
}

