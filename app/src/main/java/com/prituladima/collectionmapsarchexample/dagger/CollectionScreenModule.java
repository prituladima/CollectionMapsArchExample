package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.anotations.ForListScreen;
import com.prituladima.collectionmapsarchexample.arch.Repository;
import com.prituladima.collectionmapsarchexample.constants.ListTasksInfoStorage;
import com.prituladima.collectionmapsarchexample.constants.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.constants.Qualifiers.LIST_QUALIFIER;

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
                                                     @ForListScreen LifecycleExecutorProducer executorProduser) {
        return new CollectionPresenters(repository, subject, executorProduser);
    }

    @Provides
    @ForListScreen
    LifecycleExecutorProducer providesLifecycleExecutorProducer(@ForListScreen Repository repository,
                                                                @ForListScreen TasksInfoStorage storage) {
        return new OperationExecutorProducer(repository, storage);
    }


    @Provides
    @Singleton
    @ForListScreen
    Repository provideCollectionRepository(@ForListScreen PublishSubject<Boolean> subject,
                                           @ForListScreen TasksInfoStorage storage) {
        return new CollectionRepository(subject, storage);
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