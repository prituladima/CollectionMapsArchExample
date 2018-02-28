package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.MainApplication;
import com.prituladima.collectionmapsarchexample.operations.OperationRunnable;
import com.prituladima.collectionmapsarchexample.repository.CollectionRepository;
import com.prituladima.collectionmapsarchexample.view.fragments.CollectionsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                CollectionScreenModule.class
        }
)
public interface Injector {

    void inject(MainApplication application);

    void inject(CollectionsFragment fragment);

}