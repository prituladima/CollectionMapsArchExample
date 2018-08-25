package com.prituladima.collectionmapsarchexample.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.prituladima.collectionmapsarchexample.Logger;
import com.prituladima.collectionmapsarchexample.R;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.Unbinder;

import static butterknife.ButterKnife.bind;

public final class ListFragment extends Fragment {

  private static final Logger LOGGER = Logger.getLogger(ListFragment.class);

  @BindView(R.id.collection_recycler_view)
  RecyclerView collectionRecyclerView;

  @BindView(R.id.amount_of_operation_textview)
  EditText amountText;

  @BindView(R.id.amount_of_par_threads_textview)
  EditText threadsText;

  private Unbinder unbinder;

  @Override
  public View onCreateView(
      LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_layout, container, false);

    unbinder = bind(this, rootView);

    collectionRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));

    return rootView;
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
  }

  @OnClick(R.id.stop_calculation)
  public void stopCalculation() {}
}
