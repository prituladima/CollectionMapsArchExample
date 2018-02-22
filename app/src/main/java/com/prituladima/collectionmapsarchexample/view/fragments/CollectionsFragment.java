package com.prituladima.collectionmapsarchexample.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.prituladima.collectionmapsarchexample.MainApplication;
import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;
import com.prituladima.collectionmapsarchexample.impl.presenters.CollectionPresenters;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectionsFragment extends Fragment implements CollectionScreenContractHolder.CollectionView {

    @BindView(R.id.collection_recycler_view)
    RecyclerView collectionRecyclerView;

    @BindView(R.id.amount_of_operation_textview)
    EditText amountText;

    @BindView(R.id.amount_of_par_threads_textview)
    EditText threadsText;

    @Inject
    CollectionPresenters presenter;


    CollectionsRecyclerViewAdapter collectionsRecyclerViewAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_collections, container, false);
        ButterKnife.bind(this, rootView);
        MainApplication.getInjector().inject(this);

        collectionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        collectionsRecyclerViewAdapter = new CollectionsRecyclerViewAdapter(getActivity());
        collectionRecyclerView.setAdapter(collectionsRecyclerViewAdapter);

        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
        presenter.attachView(this);
    }

    @Override
    public void onStop() {
        presenter.detachView();
        super.onStop();
    }

    @OnClick(R.id.start_calculation)
    public void startCalculation() {
        int amount = Integer.parseInt(amountText.getText().toString());
        int threads = Integer.parseInt(threadsText.getText().toString());
        presenter.start(amount, threads);
    }

    @Override
    public void onDataSetChanged(List<CellDTO> list) {
        collectionsRecyclerViewAdapter.setData(list);
    }

    @Override
    public void onDataIsStillLoadingError() {

    }

    @Override
    public void onCalculationCanceled() {

    }

    @Override
    public void onCalculationFinished() {

    }
}

