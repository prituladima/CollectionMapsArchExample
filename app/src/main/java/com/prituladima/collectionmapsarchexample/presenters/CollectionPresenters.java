package com.prituladima.collectionmapsarchexample.presenters;

import android.os.*;

import com.prituladima.collectionmapsarchexample.arch.*;
import com.prituladima.collectionmapsarchexample.operations.*;

import javax.inject.Inject;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

public class CollectionPresenters
    extends BasePresenter<CollectionScreenContractHolder.CollectionView>
    implements CollectionScreenContractHolder.CollectionScreenContract, Action1<Boolean> {

  private final Repository repository;
  private final PublishSubject<Boolean> subject;
  private final LifecycleExecutorProducer executorProducer;
  private final boolean isTest;

  private LifecycleExecutor operationExecutor;
  private Subscription subscription;

  @Inject
  public CollectionPresenters(
      Repository repository,
      PublishSubject<Boolean> subject,
      LifecycleExecutorProducer executorProducer,
      boolean isTest) {
    this.repository = repository;
    this.subject = subject;
    this.executorProducer = executorProducer;
    this.isTest = isTest;
  }

  @Override
  public void attachView(CollectionScreenContractHolder.CollectionView mvpView) {
    super.attachView(mvpView);
    subscription = subject.subscribe(this);
    getMvpView().onDataSetChanged(repository.get());
  }

  @Override
  public void detachView() {
    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
    super.detachView();
  }

  @Override
  public void start(int amount, int threads) {
    if (operationExecutor != null && operationExecutor.isRunning()) {
      getMvpView().onDataIsStillLoadingError();
    } else {
      repository.reset();
      getMvpView().onDataSetChanged(repository.get());
      operationExecutor = executorProducer.getExecutor(amount, threads);
      operationExecutor.start();
    }
  }

  @Override
  public void stop() {
    operationExecutor.stop();
  }

  @Override
  public void call(Boolean isLast) {
    if (isTest) publish(isLast);
    else new Handler(Looper.getMainLooper()).post(() -> publish(isLast));
  }

  private void publish(Boolean isLast) {
    if (getMvpView() != null) getMvpView().onDataSetChanged(repository.get());
    if (isLast) getMvpView().onCalculationFinished();
  }
}
