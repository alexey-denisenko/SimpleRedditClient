package com.denisenko.alexey.simple.reddit.client.top.mvp;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denisenko.alexey.simple.reddit.client.R;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top.TopListActivityCallback;
import com.denisenko.alexey.simple.reddit.client.top.di.DaggerViewComponent;
import com.denisenko.alexey.simple.reddit.client.top.di.ViewComponent;
import com.denisenko.alexey.simple.reddit.client.top.di.ViewModule;
import com.denisenko.alexey.simple.reddit.client.top.ui.TopListAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopListFragment extends Fragment implements TopListContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    TopListContract.Presenter presenter;

    private TopListAdapter adapter;

    private ViewComponent viewComponent;

    private Snackbar snackbar;

    private View rootView;

    private LinearLayoutManager layoutManager;

    private TopListActivityCallback callback;

    public TopListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            callback = (TopListActivityCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement activityCallback");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewComponent == null) {
            viewComponent = DaggerViewComponent.builder()
                    .viewModule(new ViewModule(this, callback))
                    .build();
        }
        viewComponent.inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_top_list, container, false);
        ButterKnife.bind(this, rootView);
        initRecyclerView();
        initializeSwipeRefresh();
        presenter.loadFirstPage();
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initRecyclerView() {
        adapter = new TopListAdapter(new ArrayList<>(), presenter);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();

                boolean isLoading = swipeRefreshLayout.isRefreshing();
                if (!isLoading && !presenter.isLastPage()) {
                    if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0) {
                        presenter.loadNextPage();
                    }
                }
            }
        });
    }

    private void initializeSwipeRefresh() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        swipeRefreshLayout.setOnRefreshListener(() -> presenter.refreshList());
    }

    @Override
    public void setRefreshing(boolean isLoading) {
        swipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void showFirstPage(List<TopEntry> items) {
        adapter.setItems(items);
    }

    @Override
    public void showNextPage(List<TopEntry> items) {
        adapter.addItems(items);
    }

    @Override
    public void showLoadFirstPageNetworkError() {
        showSnackbar(getString(R.string.error_no_internet),
                getString(R.string.retry),
                Snackbar.LENGTH_LONG,
                v -> presenter.loadFirstPage());
    }

    @Override
    public void showLoadFirstPageUnknownError() {
        showSnackbar(getString(R.string.error_unknown),
                getString(R.string.retry),
                Snackbar.LENGTH_LONG,
                v -> presenter.loadFirstPage());
    }

    @Override
    public void showLoadNextPageNetworkError() {
        showSnackbar(getString(R.string.error_no_internet),
                getString(R.string.retry),
                Snackbar.LENGTH_LONG,
                v -> presenter.loadNextPage());
    }

    @Override
    public void showLoadNextPageUnknownError() {
        showSnackbar(getString(R.string.error_unknown),
                getString(R.string.retry),
                Snackbar.LENGTH_LONG,
                v -> presenter.loadNextPage());
    }

    public void dismissSnackbar() {
        if (snackbar != null) {
            snackbar.dismiss();
            snackbar = null;
        }
    }

    public void showSnackbar(@NonNull String message,
                             @NonNull String action,
                             int displayTime,
                             @NonNull View.OnClickListener onClickListener) {

        dismissSnackbar();

        snackbar = Snackbar.make(rootView, message, displayTime);
        snackbar.setAction(action, onClickListener);
        snackbar.show();
    }
}
