package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.arch.constants.ListOperationDataStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OperationDataStorageModule {

    @Singleton
    @Provides
    public ListOperationDataStorage provideOperationDataStorage(){
        return new ListOperationDataStorage();
    }

}