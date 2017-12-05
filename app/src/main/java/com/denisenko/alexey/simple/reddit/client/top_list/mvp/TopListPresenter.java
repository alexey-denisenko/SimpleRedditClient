package com.denisenko.alexey.simple.reddit.client.top_list.mvp;

import com.denisenko.alexey.simple.reddit.client.common.BasePresenter;
import com.denisenko.alexey.simple.reddit.client.top_list.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top_list.TopListActivityCallback;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class TopListPresenter extends BasePresenter implements TopListContract.Presenter {

    private TopListModel model;

    private final TopListContract.View view;

    private final TopListActivityCallback callback;

    @Inject
    public TopListPresenter(TopListContract.View view,
                            TopListModel model,
                            TopListActivityCallback callback,
                            CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.model = model;
        this.callback = callback;
        this.view = view;
    }

    @Override
    public void loadFirstPage() {

        view.setRefreshing(true);

        Observable<List<TopEntry>> observable;
        if (!model.isItemsReceived()) {
            observable = model.getPage();
        } else {
            observable = model.getCachedData();
        }

        Disposable disposable = observable.subscribe(
                topEntries -> {
                    view.setRefreshing(false);
                    view.showFirstPage(topEntries);

                    if (!model.isItemsReceived()) {
                        model.addItemsToCache(topEntries);
                    }
                },
                throwable -> {
                    view.setRefreshing(false);
                    throwable.printStackTrace();

                    if (throwable instanceof IOException) {
                        view.showLoadFirstPageNetworkError();
                    } else {
                        view.showLoadFirstPageUnknownError();
                    }
                }
        );

        addSubscription(disposable);
    }

    @Override
    public void loadNextPage() {

        view.setRefreshing(true);

        model.getPage().subscribe(
                topEntries -> {
                    view.setRefreshing(false);
                    view.showNextPage(topEntries);
                    model.addItemsToCache(topEntries);
                },
                throwable -> {
                    view.setRefreshing(false);
                    throwable.printStackTrace();

                    if (throwable instanceof IOException) {
                        view.showLoadNextPageNetworkError();
                    } else {
                        view.showLoadNextPageUnknownError();
                    }
                }
        );
    }

    @Override
    public void refreshList() {
        model.clearRepository();
        loadFirstPage();
    }

    @Override
    public boolean isLastPage() {
        return model.isLastPage();
    }

    @Override
    public void onItemClick(TopEntry entry) {
        callback.startWebViewActivity(entry);
    }
}
