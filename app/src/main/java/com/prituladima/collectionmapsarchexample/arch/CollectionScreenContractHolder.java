package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.entities.Cell;

import java.util.List;

public interface CollectionScreenContractHolder {

    interface CollectionView extends MVPView {
        void onDataSetChanged(List<Cell> list);

        void onDataIsStillLoadingError();

        void onCalculationFinished();
    }

    interface CollectionScreenContract {
        void start(int amount, int threads);

        void stop();
    }

}