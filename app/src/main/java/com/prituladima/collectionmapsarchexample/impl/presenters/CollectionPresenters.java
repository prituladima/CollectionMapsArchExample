package com.prituladima.collectionmapsarchexample.impl.presenters;

import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.presenter.BasePresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Subscription;
import rx.observables.ConnectableObservable;

@Singleton
public class CollectionPresenters extends BasePresenter<CollectionScreenContractHolder.CollectionView>
        implements CollectionScreenContractHolder.CollectionScreenContract{

    @Inject
    public CollectionPresenters() {
    }

    Subscription subscription;
    ConnectableObservable<Long> cold;

    @Override
    public void attachView(CollectionScreenContractHolder.CollectionView mvpView) {
        super.attachView(mvpView);

    }

    @Override
    public void detachView() {
        super.detachView();
        if(subscription != null && subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }

    @Override
    public void start(int amount, int threads) {

    }

    @Override
    public void stop() {

    }
}
