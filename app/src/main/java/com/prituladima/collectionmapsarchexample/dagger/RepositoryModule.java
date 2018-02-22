package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.arch.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    @Named(LIST_NAME)
    public Repository provideCollectionRepository(@Named(LIST_NAME) PublishSubject subject){
        return new CollectionRepository(subject);
    }

}