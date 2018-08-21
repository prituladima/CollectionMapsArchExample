package com.prituladima.collectionmapsarchexample.arch;

public interface Presenter<T> {

  void attachView(T t);

  void detachView();
}
