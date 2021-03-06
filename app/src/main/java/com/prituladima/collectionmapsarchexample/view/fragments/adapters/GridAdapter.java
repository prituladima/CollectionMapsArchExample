package com.prituladima.collectionmapsarchexample.view.fragments.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.*;

import com.prituladima.collectionmapsarchexample.R;
import com.prituladima.collectionmapsarchexample.arch.TasksInfoStorage;
import com.prituladima.collectionmapsarchexample.entities.Cell;

import java.util.*;

import javax.inject.Inject;

import butterknife.*;

public final class GridAdapter extends RecyclerView.Adapter<GridAdapter.ViewHolder> {

  private TasksInfoStorage storage;
  private List<Cell> data = new ArrayList<>();

  @Inject
  public GridAdapter(TasksInfoStorage storage) {
    this.storage = storage;
  }

  public synchronized void setData(List<Cell> listOfResult) {
    data.clear();
    data.addAll(listOfResult);
    notifyDataSetChanged();
  }

  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.recyclerview_cell_item, parent, false);
    return new ViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    String impl = storage.get().get(position).getDataType().get();
    String oper = storage.get().get(position).getTaskType().value();

    holder.labelText.setText(impl + "\n" + oper);
    holder.myTextView.setText(String.valueOf(data.get(position).getTime()));

    holder.progressBar.setVisibility(View.INVISIBLE);
    holder.myTextView.setVisibility(View.INVISIBLE);
    holder.collectionsError.setVisibility(View.INVISIBLE);

    if (data.get(position).isLoading()) {
      holder.progressBar.setVisibility(View.VISIBLE);
    } else if (data.get(position).getTime() == -1) {
      holder.collectionsError.setVisibility(View.VISIBLE);
    } else {
      holder.myTextView.setVisibility(View.VISIBLE);
    }
  }

  @Override
  public int getItemCount() {
    return data.size();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.labelText)
    TextView labelText;

    @BindView(R.id.collectionsText)
    TextView myTextView;

    @BindView(R.id.collectionsProgressBar)
    ProgressBar progressBar;

    @BindView(R.id.collectionsError)
    ImageView collectionsError;

    @BindView(R.id.progressConstraint)
    LinearLayout constraintLayout;

    ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
