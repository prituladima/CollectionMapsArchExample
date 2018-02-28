package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.entity.CellDTO;

import java.util.List;

public interface CollectionScreenContractHolder {

    interface CollectionView extends MVPView {
        void onDataSetChanged(List<CellDTO> list);

        void onDataIsStillLoadingError();

        void onCalculationFinished();
    }

    interface CollectionScreenContract {
        void start(int amount, int threads);

        void stop();
    }

}