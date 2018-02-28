package com.prituladima.collectionmapsarchexample.arch.constants;

import java.util.ArrayList;
import java.util.List;

import com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;
import com.prituladima.collectionmapsarchexample.arch.entity.OperationParamHolder;


public final class ListOperationDataStorage implements OperationDataStorage {

    private List<OperationParamHolder> list;

    public ListOperationDataStorage() {
        list = new ArrayList<>();
        int index = 0;
        for (ListOperation operation : ListOperation.values())
            for (ListImplementation implementation : ListImplementation.values())
                list.add(new OperationParamHolder(operation, implementation, index++));
    }

    @Override
    public synchronized List<OperationParamHolder> get() {
        return new ArrayList<>(list);
    }

}
