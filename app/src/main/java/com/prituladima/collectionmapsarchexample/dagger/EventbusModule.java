package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.List;

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
    public PublishSubject providesCollectionPublishSubject(){
        return PublishSubject.create();
    }





}