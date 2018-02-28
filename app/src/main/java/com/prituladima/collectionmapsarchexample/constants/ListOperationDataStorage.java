package com.prituladima.collectionmapsarchexample.constants;

import com.prituladima.collectionmapsarchexample.entity.OperationParamHolder;

import java.util.ArrayList;
import java.util.List;


public final class ListOperationDataStorage implements OperationDataStorage {

    private List<OperationParamHolder> list;

    public ListOperationDataStorage() {
        list = new ArrayList<>();
        int index = 0;
        for (OperationEnumHolder.ListOperationEnumHolder.ListOperation operation : OperationEnumHolder.ListOperationEnumHolder.ListOperation.values())
            for (OperationEnumHolder.ListOperationEnumHolder.ListImplementation implementation : OperationEnumHolder.ListOperationEnumHolder.ListImplementation.values())
                list.add(new OperationParamHolder(operation, implementation, index++));
    }

    @Override
    public synchronized List<OperationParamHolder> get() {
        return new ArrayList<>(list);
    }

}
