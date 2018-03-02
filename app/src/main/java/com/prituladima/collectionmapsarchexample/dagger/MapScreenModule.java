package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.anotations.ForListScreen;
import com.prituladima.collectionmapsarchexample.anotations.ForMapScreen;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.processors.factory.ListProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.MapProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.repository.MapTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

@Module
public class MapScreenModule {

    //1
    @Provides
    @ForMapScreen
    GridAdapter provideListGridAdapter(@ForMapScreen TasksInfoStorage storage) {
        return new GridAdapter(storage);
    }

    //2
    @Provides
    @Singleton
    @ForMapScreen
    CollectionPresenters provideCollectionPresenters(@ForMapScreen Repository repository,
                                                     @ForMapScreen PublishSubject<Boolean> subject,
                                                     @ForMapScreen LifecycleExecutorProducer executorProducer) {
        return new CollectionPresenters(repository, subject, executorProducer);
    }

    @Provides
    @ForMapScreen
    LifecycleExecutorProducer providesLifecycleExecutorProducer(@ForMapScreen Repository repository,
                                                                @ForMapScreen TasksInfoStorage storage,
                                                                @ForMapScreen ProcessorFactory factory) {
        return new OperationExecutorProducer(repository, storage, factory);
    }


    @Provides
    @Singleton
    @ForMapScreen
    Repository provideCollectionRepository(@ForMapScreen PublishSubject<Boolean> subject,
                                           @ForMapScreen TasksInfoStorage storage) {
        return new CellRepository(subject, storage);
    }

    //leaf
    @Provides
    @ForMapScreen
    ProcessorFactory providesProcessorFactory(){
        return new MapProcessorFactory();
    }


    //leaf
    @Provides
    @Singleton
    @ForMapScreen
    TasksInfoStorage provideOperationDataStorage() {
        return new MapTasksInfoStorage();
    }

    //leaf
    @Singleton
    @Provides
    @ForMapScreen
    PublishSubject<Boolean> providesCollectionPublishSubject() {
        return PublishSubject.create();
    }



}
