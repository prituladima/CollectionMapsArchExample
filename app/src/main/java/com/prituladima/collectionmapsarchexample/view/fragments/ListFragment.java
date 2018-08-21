package com.prituladima.collectionmapsarchexample.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.EditText;

import com.prituladima.collectionmapsarchexample.*;
import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.anotations.ForListScreen;
import com.prituladima.collectionmapsarchexample.arch.CollectionScreenContractHolder;
import com.prituladima.collectionmapsarchexample.entities.Cell;
import com.prituladima.collectionmapsarchexample.presenters.CollectionPresenters;
import com.prituladima.collectionmapsarchexample.view.fragments.adapters.GridAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.*;

import static android.widget.Toast.*;
import static butterknife.ButterKnife.bind;
import static com.prituladima.collectionmapsarchexample.MainApplication.getApplicationComponent;

public final class ListFragment extends Fragment
    implements CollectionScreenContractHolder.CollectionView {

  private static final Logger LOGGER = Logger.getLogger(ListFragment.class);

  @BindView(R.id.collection_recycler_view)
  RecyclerView collectionRecyclerView;

  @BindView(R.id.amount_of_operation_textview)
  EditText amountText;

  @BindView(R.id.amount_of_par_threads_textview)
  EditText threadsText;

  @Inject @ForListScreen CollectionPresenters presenter;

  @Inject @ForListScreen GridAdapter adapter;

  private Unbinder unbinder;

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

    getApplicationComponent().inject(this);

    unbinder = bind(this, rootView);

    collectionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

    collectionRecyclerView.setAdapter(adapter);

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

  @Override
  public void onDestroyView() {
    unbinder.unbind();
    super.onDestroyView();
  }

  @OnClick(R.id.start_calculation)
  public void startCalculation() {
    int amount = Integer.parseInt(amountText.getText().toString());
    int threads = Integer.parseInt(threadsText.getText().toString());
    presenter.start(amount, threads);
  }

  @OnClick(R.id.stop_calculation)
  public void stopCalculation() {
    presenter.stop();
  }

  @Override
  public void onDataSetChanged(List<Cell> list) {
    LOGGER.log(list.toString());
    adapter.setData(list);
  }

  @Override
  public void onDataIsStillLoadingError() {
    makeText(getActivity(), "ListTaskType is still running!", LENGTH_SHORT).show();
  }

  @Override
  public void onCalculationFinished() {
    makeText(getActivity(), "Calculation finished!", LENGTH_SHORT).show();
  }
}
