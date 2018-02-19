package com.prituladima.collectionmapsarchexample.arch;

import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;
import com.prituladima.collectionmapsarchexample.arch.view.MVPView;

import java.util.List;

/**
 * Created by prituladima on 2/18/18.
 */

public interface CollectionScreenContractHolder {

    interface CollectionView extends MVPView {
        void onDataSetChanged(List<CellDTO> list);
        void onDataIsStillLoadingError();
        void onCalculationCanceled();
        void onCalculationFinished();
    }

    interface CollectionScreenContract {
        void startCalculationForAmount(int amount);
        void stopCalculation();
    }


}
