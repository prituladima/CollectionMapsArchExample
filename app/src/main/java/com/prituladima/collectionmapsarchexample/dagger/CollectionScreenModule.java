package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.constants.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.operations.LifecycleExecutorProducer;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationExecutorProducer;
import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;
import com.prituladima.collectionmapsarchexample.impl.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

@Module
class CollectionScreenModule {

    //1
    @Provides
    @Named(LIST_NAME)
    GridAdapter provideListGridAdapter(@Named(LIST_NAME) OperationDataStorage storage) {
        return new GridAdapter(storage);
    }

    //2
    @Provides
    @Singleton
    @Named(LIST_NAME)
    CollectionPresenters provideCollectionPresenters(@Named(LIST_NAME) Repository repository,
                                                     @Named(LIST_NAME) PublishSubject<Boolean> subject,
                                                     LifecycleExecutorProducer executorProduser) {
        return new CollectionPresenters(repository, subject, executorProduser);
    }

    @Provides
    LifecycleExecutorProducer providesLifecycleExecutorProduser(@Named(LIST_NAME) Repository repository,
                                                                @Named(LIST_NAME) OperationDataStorage storage) {
        return new OperationExecutorProducer(repository, storage);
    }


    @Provides
    @Singleton
    @Named(LIST_NAME)
    Repository provideCollectionRepository(@Named(LIST_NAME) PublishSubject<Boolean> subject,
                                           @Named(LIST_NAME) OperationDataStorage storage) {
        return new CollectionRepository(subject, storage);
    }

    //leaf
    @Provides
    @Singleton
    @Named(LIST_NAME)
    OperationDataStorage provideOperationDataStorage() {
        return new ListOperationDataStorage();
    }

    //leaf
    @Singleton
    @Provides
    @Named(LIST_NAME)
    PublishSubject<Boolean> providesCollectionPublishSubject() {
        return PublishSubject.create();
    }


}