package com.prituladima.collectionmapsarchexample;

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

