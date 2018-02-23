package com.prituladima.collectionmapsarchexample.arch.repository;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.List;

public interface Repository {

    void put(int position, long time, boolean isLoading);

    List<CellDTO> get();

    List<CellDTO> getDefault();

    void reset();

}
