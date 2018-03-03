package com.prituladima.collectionmapsarchexample.presenters;


import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.Cell;
import com.prituladima.collectionmapsarchexample.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.processors.factory.ListProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CollectionPresentersTest {


    private PublishSubject<Boolean> subject;
    private ProcessorFactory factory;
    private TasksInfoStorage storage;
    private int storageSize;
    private Repository repository;
    private LifecycleExecutorProducer producer;
    private CollectionPresenters presenters;

    private void init() {
        subject = PublishSubject.create();
        factory = new ListProcessorFactory();
        storage = new ListTasksInfoStorage();
        storageSize = storage.get().size();
        repository = new CellRepository(subject, storage);
        producer = new OperationExecutorProducer(repository, storage, factory);

        presenters = new CollectionPresenters(repository, subject, producer, true);

    }

    @Test(expected = NullPointerException.class)
    public void testPresenterException() {
        init();
        presenters.start(-1, -10);
    }

    private void assertList(List<Cell> list) {
        assertNotNull(list);
        assertEquals(storageSize, list.size());
        for (Cell cell : list)
            assertNotNull(cell);
    }

    @Test(timeout = 1000)
    public void testPresenterCallbackResults() throws InterruptedException {
        init();
        CountDownLatch countDownLatch = new CountDownLatch(storageSize * 2 + 4);
        presenters.attachView(new CollectionScreenContractHolder.CollectionView() {
            @Override
            public void onDataSetChanged(List<Cell> list) {
                assertList(list);
                countDownLatch.countDown();
            }

            @Override
            public void onDataIsStillLoadingError() {

            }

            @Override
            public void onCalculationFinished() {
                countDownLatch.countDown();
            }
        });

        presenters.start(1000, 4);

        countDownLatch.await();

        assertEquals(0, countDownLatch.getCount());

    }


    @Test(timeout = 1000)
    public void testPresenterCallbackError() throws InterruptedException {
        init();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        presenters.attachView(new CollectionScreenContractHolder.CollectionView() {
            @Override
            public void onDataSetChanged(List<Cell> list) {
                assertList(list);
            }

            @Override
            public void onDataIsStillLoadingError() {
                countDownLatch.countDown();
            }

            @Override
            public void onCalculationFinished() {

            }
        });

        presenters.start(10, 4);
        presenters.start(10, 4);

        countDownLatch.await();

        assertEquals(0, countDownLatch.getCount());


    }


    @Test(timeout = 1000)
    public void testPresenterCallbackFinished() throws InterruptedException {
        init();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        presenters.attachView(new CollectionScreenContractHolder.CollectionView() {
            @Override
            public void onDataSetChanged(List<Cell> list) {
                assertList(list);
            }

            @Override
            public void onDataIsStillLoadingError() {

            }

            @Override
            public void onCalculationFinished() {
                countDownLatch.countDown();
            }
        });


        presenters.start(10, 4);
        presenters.stop();

        countDownLatch.await();

        assertEquals(0, countDownLatch.getCount());

    }

}