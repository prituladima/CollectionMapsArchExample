package com.prituladima.collectionmapsarchexample.dagger;

import com.prituladima.collectionmapsarchexample.anotations.ForMapScreen;
import com.prituladima.collectionmapsarchexample.arch.*;
import com.prituladima.collectionmapsarchexample.operations.*;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.processors.factory.*;
import com.prituladima.collectionmapsarchexample.repository.*;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import javax.inject.Singleton;

import dagger.*;
import rx.subjects.PublishSubject;

@Module
public class MapScreenModule {

  // 1
  @Provides
  @ForMapScreen
  GridAdapter provideListGridAdapter(@ForMapScreen TasksInfoStorage storage) {
    return new GridAdapter(storage);
  }

  // 2
  @Provides
  @Singleton
  @ForMapScreen
  CollectionPresenters provideCollectionPresenters(
      @ForMapScreen Repository repository,
      @ForMapScreen PublishSubject<Boolean> subject,
      @ForMapScreen LifecycleExecutorProducer executorProducer) {
    return new CollectionPresenters(repository, subject, executorProducer, false);
  }

  @Provides
  @ForMapScreen
  LifecycleExecutorProducer providesLifecycleExecutorProducer(
      @ForMapScreen Repository repository,
      @ForMapScreen TasksInfoStorage storage,
      @ForMapScreen ProcessorFactory factory) {
    return new OperationExecutorProducer(repository, storage, factory);
  }

  @Provides
  @Singleton
  @ForMapScreen
  Repository provideCollectionRepository(
      @ForMapScreen PublishSubject<Boolean> subject, @ForMapScreen TasksInfoStorage storage) {
    return new CellRepository(subject, storage);
  }

  // leaf
  @Provides
  @ForMapScreen
  ProcessorFactory providesProcessorFactory() {
    return new MapProcessorFactory();
  }

  // leaf
  @Provides
  @Singleton
  @ForMapScreen
  TasksInfoStorage provideOperationDataStorage() {
    return new MapTasksInfoStorage();
  }

  // leaf
  @Singleton
  @Provides
  @ForMapScreen
  PublishSubject<Boolean> providesCollectionPublishSubject() {
    return PublishSubject.create();
  }
}
