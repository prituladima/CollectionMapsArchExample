package com.prituladima.collectionmapsarchexample.collection;

import com.prituladima.collectionmapsarchexample.arch.BasePresenter;
import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.Cell;
import com.prituladima.collectionmapsarchexample.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.processors.factory.ListProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import rx.subjects.PublishSubject;

import static org.junit.Assert.*;

public class CollectionPresenterTest {

    private PublishSubject<Boolean> subject;
    private TasksInfoStorage storage;
    private CollectionPresenters presenters;
    private Repository repository;
    private LifecycleExecutorProducer producer;
    private ProcessorFactory factory;

    public void init(){
        subject = PublishSubject.create();
        factory = new ListProcessorFactory();
        storage =  new ListTasksInfoStorage();
        repository = new CellRepository(subject, storage);
        producer = new OperationExecutorProducer(repository, storage, factory);

        presenters = new CollectionPresenters(repository, subject, producer);

    }

    @Test(expected = NullPointerException.class)
    public void testPresenterException(){
        init();
        presenters.start(-1, 0);
    }

    @Test
    public void testPresenterAttach(){
        init();
        presenters.attachView(new CollectionScreenContractHolder.CollectionView() {
            @Override
            public void onDataSetChanged(List<Cell> list) {
                assertNotNull(list);
            }

            @Override
            public void onDataIsStillLoadingError() {

            }

            @Override
            public void onCalculationFinished() {

            }
        });

        presenters.start(10000, 4);

        //presenters.stop();



    }

}
