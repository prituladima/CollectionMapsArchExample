package com.prituladima.collectionmapsarchexample.presenter;

public interface Presenter<T> {

    void attachView(T t);

    void detachView();

}
