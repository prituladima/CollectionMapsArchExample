package com.prituladima.collectionmapsarchexample.impl.presenters;

import android.os.Handler;
import android.os.Looper;

import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.operations.OperationExecutor;
import com.prituladima.collectionmapsarchexample.arch.presenter.BasePresenter;
import com.prituladima.collectionmapsarchexample.arch.constants.OperationDataStorage;
import com.prituladima.collectionmapsarchexample.arch.repository.Repository;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import rx.Subscription;
import rx.functions.Action1;
import rx.subjects.PublishSubject;

import static com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.LIST_NAME;

@Singleton
public class CollectionPresenters extends BasePresenter<CollectionScreenContractHolder.CollectionView>
        implements CollectionScreenContractHolder.CollectionScreenContract, Action1<Boolean> {

    private Repository repository;
    private PublishSubject<Boolean> subject;
    private Subscription subscription;
    private ExecutorService service;
    private OperationDataStorage storage;

    private OperationExecutor operationExecutor;

    @Inject
    public CollectionPresenters(@Named(LIST_NAME) Repository repository,
                                @Named(LIST_NAME) PublishSubject<Boolean> subject,
                                OperationDataStorage storage) {
        this.repository = repository;
        this.subject = subject;
        this.storage = storage;
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
            operationExecutor = new OperationExecutor.OperationExecutorBuilder(new CountDownLatch(storage.getList().size()), threads, amount, repository, storage).build();
            operationExecutor.start();
        }
    }

    @Override
    public void stop() {
        operationExecutor.stop();
    }

    @Override
    public void call(Boolean isLast) {
        new Handler(Looper.getMainLooper()).post(() ->
                {
                    if (getMvpView() != null)
                        getMvpView().onDataSetChanged(repository.get());
                    if (isLast)
                        getMvpView().onCalculationFinished();
                }
        );
    }
}
