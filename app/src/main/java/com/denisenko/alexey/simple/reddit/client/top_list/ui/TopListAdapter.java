package com.denisenko.alexey.simple.reddit.client.top_list.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denisenko.alexey.simple.reddit.client.R;
import com.denisenko.alexey.simple.reddit.client.top_list.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListContract;

import java.util.List;

public class TopListAdapter extends RecyclerView.Adapter<TopListViewHolder> {

    private final List<TopEntry> topEntries;
    private final TopListContract.Presenter presenter;


    public TopListAdapter(List<TopEntry> topEntries, TopListContract.Presenter presenter) {
        this.topEntries = topEntries;
        this.presenter = presenter;
    }

    @Override
    public TopListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new TopListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopListViewHolder holder, int position) {
        holder.bind(topEntries.get(position));
        holder.image.setOnClickListener(v -> presenter.onItemClick(topEntries.get(position)));
    }

    @Override
    public int getItemCount() {
        return topEntries.size();
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.item_top_list;
    }

    public List<TopEntry> getItems() {
        return topEntries;
    }

    public void setItems(List<TopEntry> items) {
        topEntries.clear();
        topEntries.addAll(items);
        notifyDataSetChanged();
    }

    public void addItems(List<TopEntry> items) {
        topEntries.addAll(items);
        notifyDataSetChanged();
    }
}
