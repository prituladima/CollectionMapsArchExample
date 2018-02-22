package com.prituladima.collectionmapsarchexample.view.fragments;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.arch.dto.CellDTO;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionsAdapter extends RecyclerView.Adapter<CollectionsAdapter.ViewHolder> {


    private List<CellDTO> data = new ArrayList<>();

    private LayoutInflater mInflater;

    public void setData(List<CellDTO> listOfResult) {
        data.clear();
        data.addAll(listOfResult);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_of_collections, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
            //String result = data.get(position). + data.get(position).getCount();
            if (data.get(position).isLoading()) {
                holder.progressBar.setVisibility(View.VISIBLE);
                holder.myTextView.setVisibility(View.INVISIBLE);
            } else {
                holder.progressBar.setVisibility(View.INVISIBLE);
                holder.myTextView.setVisibility(View.VISIBLE);
                holder.myTextView.setText(String.valueOf(data.get(position).getTime()));
            }


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.collectionsText)
        TextView myTextView;

        @BindView(R.id.collectionsProgressBar)
        ProgressBar progressBar;

        @BindView(R.id.progressConstraint)
        FrameLayout constraintLayout;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}
