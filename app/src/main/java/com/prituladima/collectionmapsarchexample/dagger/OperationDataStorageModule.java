package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OperationDataStorageModule {

    @Singleton
    @Provides
    public OperationDataStorage provideOperationDataStorage(){
        return OperationDataStorage.getInstance();
    }

}