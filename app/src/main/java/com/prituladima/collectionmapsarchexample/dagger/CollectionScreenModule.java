package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.anotations.ForListScreen;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.processors.factory.ListProcessorFactory;
import com.prituladima.collectionmapsarchexample.processors.factory.ProcessorFactory;
import com.prituladima.collectionmapsarchexample.repository.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.repository.CellRepository;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

@Module
class CollectionScreenModule {

    //1
    @Provides
    @ForListScreen
    GridAdapter provideListGridAdapter(@ForListScreen TasksInfoStorage storage) {
        return new GridAdapter(storage);
    }

    //2
    @Provides
    @Singleton
    @ForListScreen
    CollectionPresenters provideCollectionPresenters(@ForListScreen Repository repository,
                                                     @ForListScreen PublishSubject<Boolean> subject,
                                                     @ForListScreen LifecycleExecutorProducer executorProducer) {
        return new CollectionPresenters(repository, subject, executorProducer, false);
    }

    @Provides
    @ForListScreen
    LifecycleExecutorProducer providesLifecycleExecutorProducer(@ForListScreen Repository repository,
                                                                @ForListScreen TasksInfoStorage storage,
                                                                @ForListScreen ProcessorFactory factory) {
        return new OperationExecutorProducer(repository, storage, factory);
    }



    @Provides
    @Singleton
    @ForListScreen
    Repository provideCollectionRepository(@ForListScreen PublishSubject<Boolean> subject,
                                           @ForListScreen TasksInfoStorage storage) {
        return new CellRepository(subject, storage);
    }

    //leaf
    @Provides
    @ForListScreen
    ProcessorFactory providesProcessorFactory(){
        return new ListProcessorFactory();
    }

    //leaf
    @Provides
    @Singleton
    @ForListScreen
    TasksInfoStorage provideOperationDataStorage() {
        return new ListTasksInfoStorage();
    }

    //leaf
    @Singleton
    @Provides
    @ForListScreen
    PublishSubject<Boolean> providesCollectionPublishSubject() {
        return PublishSubject.create();
    }


}