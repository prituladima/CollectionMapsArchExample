package com.prituladima.collectionmapsarchexample.dagger;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

@Module
public class EventbusModule {

    @Singleton
    @Provides
    @Named(LIST_NAME)
    public PublishSubject<Boolean> providesCollectionPublishSubject(){
        return PublishSubject.create();
    }





}