package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.MainApplication;
import com.prituladima.collectionmapsarchexample.view.fragments.*;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CollectionScreenModule.class, MapScreenModule.class})
public interface ApplicationComponent {

  void inject(MainApplication application);

  void inject(ListFragment fragment);

  void inject(MapsFragment fragment);
}
