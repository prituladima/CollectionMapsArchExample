package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.arch.entity.CellDTO;
import com.prituladima.collectionmapsarchexample.arch.entity.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationExecutor;
import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionThreadPoolTest {
    private static final int EXPECTED_SIZE = 21;

    private static final int THREADS = 4;
    private static final int AMOUNT = 10_000;

    private PublishSubject subject;
    private CollectionRepository repository;
    private CountDownLatch countDownLatch;
    private ListOperationDataStorage storage;
    private List<OperationParamHolder> holders;

    @Before
    public void init(){
        subject = PublishSubject.create();
        storage = new ListOperationDataStorage();
        repository = new CollectionRepository(subject, storage);
        countDownLatch = new CountDownLatch(EXPECTED_SIZE);
        holders = storage.getList();
    }

    @Test
    public void amountInStorageTest() {
        assertEquals(EXPECTED_SIZE, holders.size());
    }

    @Test
    public void threadPoolRunningTest() throws InterruptedException{

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage).build();
        operationExecutor.start();

        countDownLatch.await();

        for (int i = 0; i < EXPECTED_SIZE; i++) {
            CellDTO cell = repository.get().get(i);
            OperationParamHolder holder = holders.get(i);
            System.out.println(" --- " + cell + " --- " + holder);
            assertTrue(cell.getTime() >= 0);
            assertFalse(cell.isLoading());
        }

    }

    @Test(expected = ProcessorIsStillRunningException.class)
    public void threadPoolRunningTwice() {

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(EXPECTED_SIZE), THREADS, AMOUNT, repository, storage).build();
        operationExecutor.start();
        operationExecutor.start();

    }

    @Test(timeout = 1_000)
    public void threadStopped() throws InterruptedException{

        CountDownLatch countDownLatch =  new CountDownLatch(EXPECTED_SIZE);

        assertEquals(EXPECTED_SIZE, countDownLatch.getCount());

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        countDownLatch.await();

        assertEquals(0L, countDownLatch.getCount());
        assertFalse(operationExecutor.isRunning());
    }

    @Test(expected = ProcessorIsStillRunningException.class)
    public void threadStoppedAndRunAgain() throws InterruptedException{

        CountDownLatch countDownLatch =  new CountDownLatch(EXPECTED_SIZE);


        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        operationExecutor.start();
    }

    @Test
    public void threadStoppedTwice() throws InterruptedException{

        CountDownLatch countDownLatch =  new CountDownLatch(EXPECTED_SIZE);


        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        operationExecutor.stop();

        assertTrue(operationExecutor.isRunning());

        countDownLatch.await();

        assertFalse(operationExecutor.isRunning());
    }

}