package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.processors.factory.ListProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.TaskInfo;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.Cell;
import com.prituladima.collectionmapsarchexample.exceptions.ProcessorIsStillRunningException;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutor;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;

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
    private static final int AMOUNT = 10000;

    private PublishSubject<Boolean> subject;
    private CellRepository repository;
    private CountDownLatch countDownLatch;
    private TasksInfoStorage storage;
    private List<TaskInfo> holders;
    private ProcessorFactory factory;

    @Before
    public void init() {
        subject = PublishSubject.create();
        storage = new ListTasksInfoStorage();
        repository = new CellRepository(subject, storage);
        countDownLatch = new CountDownLatch(EXPECTED_SIZE);
        holders = storage.get();
        factory = new ListProcessorFactory();
    }

    @Test
    public void testAmountInStorageTest() {
        assertEquals(EXPECTED_SIZE, holders.size());
    }

    @Test
    public void testThreadPoolRunningTest() throws InterruptedException {

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage, factory).build();
        operationExecutor.start();

        countDownLatch.await();

        for (int i = 0; i < EXPECTED_SIZE; i++) {
            Cell cell = repository.get().get(i);
            TaskInfo holder = holders.get(i);
            System.out.println(" --- " + cell + " --- " + holder);
            assertTrue(cell.getTime() >= 0);
            assertFalse(cell.isLoading());
        }

//        TestSubscriber<Boolean> testSubscriber = new TestSubscriber<>();
//        subject.subscribe(testSubscriber);

//        testSubscriber.assertCompleted();
//        testSubscriber.awaitTerminalEvent();

    }

    @Test(expected = ProcessorIsStillRunningException.class)
    public void testThreadPoolRunningTwice() {

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(EXPECTED_SIZE), THREADS, AMOUNT, repository, storage, factory).build();
        operationExecutor.start();
        operationExecutor.start();

    }

    @Test(timeout = 1_000)
    public void testThreadStopped() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);

        assertEquals(EXPECTED_SIZE, countDownLatch.getCount());

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage, factory).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        countDownLatch.await();

        assertEquals(0L, countDownLatch.getCount());
        assertFalse(operationExecutor.isRunning());
    }

    @Test(expected = ProcessorIsStillRunningException.class)
    public void testThreadStoppedAndRunAgain() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);


        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage, factory).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        operationExecutor.start();
    }

    @Test
    public void testThreadStoppedTwice() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);


        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, storage, factory).build();
        operationExecutor.start();
        assertTrue(operationExecutor.isRunning());
        operationExecutor.stop();

        operationExecutor.stop();

        assertTrue(operationExecutor.isRunning());

        countDownLatch.await();

        assertFalse(operationExecutor.isRunning());
    }



    @Test(expected = IllegalArgumentException.class)
    public void testCountDownLatchError() throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(-1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCountDownLatchNullError() throws InterruptedException {

        OperationExecutor operationExecutor =
                new OperationExecutor.OperationExecutorBuilder(null, THREADS, AMOUNT, repository, storage, factory).build();


    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalThreadsAmountError() {

        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);

        new OperationExecutor.OperationExecutorBuilder(countDownLatch, -20, AMOUNT, repository, storage, factory).build();


    }

    @Test(expected = IllegalArgumentException.class)
    public void testIllegalTasksAmountError() {

        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);
        new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, -20, repository, storage, factory).build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepositoryNullError(){
        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);
        new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, null, storage, factory).build();

    }

    @Test(expected = IllegalArgumentException.class)
    public void threadCreatedWithError(){
        CountDownLatch countDownLatch = new CountDownLatch(EXPECTED_SIZE);
        new OperationExecutor.OperationExecutorBuilder(countDownLatch, THREADS, AMOUNT, repository, null, factory).build();
    }
}