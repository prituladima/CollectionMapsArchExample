package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;
import com.prituladima.collectionmapsarchexample.arch.view.MVPView;

import java.util.List;

public interface CollectionScreenContractHolder {

    interface CollectionView extends MVPView {
        void onDataSetChanged(List<CellDTO> list);
        void onDataIsStillLoadingError();
        void onCalculationCanceled();
        void onCalculationFinished();
    }

    interface CollectionScreenContract {
        void start(int amount, int threads);
        void stop();
    }

}