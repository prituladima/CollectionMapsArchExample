package com.prituladima.collectionmapsarchexample.arch.repository;

import java.util.ArrayList;
import java.util.List;

import com.prituladima.collectionmapsarchexample.arch.constants.OperationEnumHolder.ListOperationEnumHolder.*;
import com.prituladima.collectionmapsarchexample.arch.dto.OperationParamHolder;


public class OperationDataStorage {

    private static OperationDataStorage instance;

    public static synchronized OperationDataStorage getInstance() {
        if (instance == null) {
            instance = new OperationDataStorage();
        }
        return instance;
    }

    private List<OperationParamHolder> list = new ArrayList<>();

    private OperationDataStorage() {
        init();
    }

    private void init() {
        int index = 0;
        for (Operation operation : Operation.values())
            for (Implementation implementation : Implementation.values())
                list.add(new OperationParamHolder(operation, implementation, index++));
    }

    public synchronized List<OperationParamHolder> getList() {
        return new ArrayList<>(list);
    }

}
