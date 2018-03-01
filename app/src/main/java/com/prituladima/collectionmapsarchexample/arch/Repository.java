package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.entity.CellDTO;

import java.util.List;

public interface Repository {
    void put(int position, long time, boolean isLoading, boolean isLast);

    List<CellDTO> get();

    List<CellDTO> getDefault();

    void reset();
}