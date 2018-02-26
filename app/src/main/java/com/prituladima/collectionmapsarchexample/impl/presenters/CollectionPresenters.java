package com.prituladima.collectionmapsarchexample.impl.presenters;

import android.os.Handler;
import android.os.Looper;

import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationRunnable;
import com.prituladima.collectionmapsarchexample.arch.presenter.BasePresenter;
import com.prituladima.collectionmapsarchexample.arch.repository.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
        service = Executors.newFixedThreadPool(threads);
        repository.reset();
        getMvpView().onDataSetChanged(repository.get());
        for (OperationParamHolder holder : OperationDataStorage.getInstance().getList()) {
            service.submit(new OperationRunnable(holder, amount, repository));
        }

        service.shutdown();
    }

    @Override
    public void stop() {
        service.shutdownNow();
    }

    @Override
    public void call(Object ignore) {
        new Handler(Looper.getMainLooper()).post(() ->
                {
                    if (getMvpView() != null)
                        getMvpView().onDataSetChanged(repository.get());
                }
        );
    }
}
