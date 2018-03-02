package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.entities.Cell;

import java.util.List;

public interface Repository {
    void put(int position, long time, boolean isLoading, boolean isLast);

    List<Cell> get();

    void reset();
}
