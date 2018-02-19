package com.prituladima.collectionmapsarchexample.arch.presenter;

public interface Presenter<T> {

    void attachView(T t);

    void detachView();

}
