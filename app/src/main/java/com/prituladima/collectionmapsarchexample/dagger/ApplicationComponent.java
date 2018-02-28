package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.MainApplication;
import com.prituladima.collectionmapsarchexample.view.fragments.CollectionsFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(
        modules = {
                CollectionScreenModule.class
        }
)
public interface ApplicationComponent {

    void inject(MainApplication application);

    void inject(CollectionsFragment fragment);

}