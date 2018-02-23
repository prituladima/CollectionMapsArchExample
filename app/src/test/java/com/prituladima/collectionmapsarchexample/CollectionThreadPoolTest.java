package com.prituladima.collectionmapsarchexample;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;
import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationRunnable;
import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import rx.subjects.PublishSubject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CollectionThreadPoolTest {
    private static final int EXPECTED_SIZE = 21;

    private static final int THREADS = 4;
    private static final int AMOUNT = 100_000;

    private ExecutorService service;
    private PublishSubject subject;
    private CollectionRepository repository;
    private CountDownLatch countDownLatch;

    @Before
    public void init(){
        service = Executors.newFixedThreadPool(THREADS);
        subject = PublishSubject.create();
        repository = new CollectionRepository(subject);
        countDownLatch = new CountDownLatch(EXPECTED_SIZE);
    }

    @Test
    public void threadPoolTest() throws InterruptedException{
        List<OperationParamHolder> holders = OperationDataStorage.getInstance().getList();

        assertEquals(EXPECTED_SIZE, holders.size());

        for (OperationParamHolder holder : holders) {
            service.submit(new OperationRunnable(holder, AMOUNT, repository, countDownLatch));
        }
        service.shutdown();

        countDownLatch.await();

        for (int i = 0; i < EXPECTED_SIZE; i++) {
            CellDTO cell = repository.get().get(i);
            System.out.println(" --- " + cell);
            assertTrue(cell.getTime() >= 0);
            assertFalse(cell.isLoading());
        }

//        List<CellDTO> list


    }


}
