package com.prituladima.collectionmapsarchexample.arch.repository;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class CollectionRepository implements Repository{

    private List<CellDTO> data = new ArrayList<>();

    @Override
    public void put(int position, long time, boolean isLoading) {
        data.add(position, new CellDTO(time, isLoading));
    }

    @Override
    public List<CellDTO> get() {
        return  data;
    }
}
