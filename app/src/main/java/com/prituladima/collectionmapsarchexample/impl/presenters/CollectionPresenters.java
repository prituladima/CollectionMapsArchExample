package com.prituladima.collectionmapsarchexample.impl.presenters;

import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationRunnable;
import com.prituladima.collectionmapsarchexample.arch.presenter.BasePresenter;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

@Singleton
public class CollectionPresenters extends BasePresenter<CollectionScreenContractHolder.CollectionView>
        implements CollectionScreenContractHolder.CollectionScreenContract, Action1 {

    private Repository repository;
    private PublishSubject subject;
    private Subscription subscription;
    private ExecutorService service;

    @Inject
    public CollectionPresenters(@Named(LIST_NAME) Repository repository,
                                @Named(LIST_NAME) PublishSubject subject) {
        this.repository = repository;
        this.subject = subject;
    }

    @Override
    public void attachView(CollectionScreenContractHolder.CollectionView mvpView) {
        super.attachView(mvpView);
        subscription = subject.subscribe(this);
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
        service = Executors.newFixedThreadPool(threads);

        Observable.from(OperationDataStorage.getInstance().getList())
                .subscribe(x -> service.submit(new OperationRunnable(x, amount)));

        service.shutdown();
    }

    @Override
    public void stop() {
        service.shutdownNow();
    }

    @Override
    public void call(Object o) {
        if (getMvpView() != null)
            getMvpView().onDataSetChanged(repository.get());
    }
}
