package com.denisenko.alexey.simple.reddit.client.top.mvp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.R;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top.ui.TopListAdapter;
import com.denisenko.alexey.simple.reddit.client.top.ui.TopListViewHolder;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListFragment extends Fragment implements TopListContract.View, TopListViewHolder.OnEntryClickListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    TopListContract.Presenter presenter;

    public TopListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_top_list, container, false);
        ButterKnife.bind(this, view);
        presenter.loanInitial();
        return view;
    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new TopListAdapter(new ArrayList<>(), this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setRefreshing(boolean isLoading) {

    }

    @Override
    public void addItems(List<TopEntry> items) {

    }

    @Override
    public void showError() {

    }

    @Override
    public void stopPagination() {

    }

    @Override
    public void onEntryClick() {

    }
}
