package com.prituladima.collectionmapsarchexample.view.fragments;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Figli on 29.01.2018.
 */

public class CollectionsRecyclerViewAdapter extends RecyclerView.Adapter<CollectionsRecyclerViewAdapter.ViewHolder> {


    private ArrayList<CellDTO> resultOfOperations = new ArrayList<>();

    private LayoutInflater mInflater;

    public ArrayList<CellDTO> getResultOfOperations() {
        return resultOfOperations;
    }

    public void setDataByIndex(int index, CellDTO model) {
        resultOfOperations.set(index, model);
        notifyDataSetChanged();
    }

    public void setData(ArrayList<CellDTO> listOfResult) {
        resultOfOperations.clear();
        resultOfOperations.addAll(listOfResult);
        notifyDataSetChanged();
    }

    public CollectionsRecyclerViewAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recyclerview_item_of_collections, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            //String result = resultOfOperations.get(position). + resultOfOperations.get(position).getCount();
            if (resultOfOperations.get(position).isLoading()) {
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.myTextView.setVisibility(View.INVISIBLE);
            } else {
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.myTextView.setVisibility(View.VISIBLE);
                //holder.myTextView.setText(result);
            }


    }

    @Override
    public int getItemCount() {
        return resultOfOperations.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.collectionsText)
        TextView myTextView;

        @BindView(R.id.collectionsProgressBar)
        ProgressBar progressBar;

        @BindView(R.id.progressConstraint)
        ConstraintLayout constraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
