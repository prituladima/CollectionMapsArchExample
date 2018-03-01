package com.prituladima.collectionmapsarchexample.dagger;

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
    @Named(LIST_QUALIFIER)
    GridAdapter provideListGridAdapter(@Named(LIST_QUALIFIER) TasksInfoStorage storage) {
        return new GridAdapter(storage);
    }

    //2
    @Provides
    @Singleton
    @Named(LIST_QUALIFIER)
    CollectionPresenters provideCollectionPresenters(@Named(LIST_QUALIFIER) Repository repository,
                                                     @Named(LIST_QUALIFIER) PublishSubject<Boolean> subject,
                                                     LifecycleExecutorProducer executorProduser) {
        return new CollectionPresenters(repository, subject, executorProduser);
    }

    @Provides
    LifecycleExecutorProducer providesLifecycleExecutorProducer(@Named(LIST_QUALIFIER) Repository repository,
                                                                @Named(LIST_QUALIFIER) TasksInfoStorage storage) {
        return new OperationExecutorProducer(repository, storage);
    }


    @Provides
    @Singleton
    @Named(LIST_QUALIFIER)
    Repository provideCollectionRepository(@Named(LIST_QUALIFIER) PublishSubject<Boolean> subject,
                                           @Named(LIST_QUALIFIER) TasksInfoStorage storage) {
        return new CollectionRepository(subject, storage);
    }

    //leaf
    @Provides
    @Singleton
    @Named(LIST_QUALIFIER)
    TasksInfoStorage provideOperationDataStorage() {
        return new ListTasksInfoStorage();
    }

    //leaf
    @Singleton
    @Provides
    @Named(LIST_QUALIFIER)
    PublishSubject<Boolean> providesCollectionPublishSubject() {
        return PublishSubject.create();
    }


}