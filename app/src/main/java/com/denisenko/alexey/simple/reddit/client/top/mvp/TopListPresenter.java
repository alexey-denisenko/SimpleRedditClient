package com.denisenko.alexey.simple.reddit.client.top.mvp;

import android.util.Log;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.common.BasePresenter;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class TopListPresenter extends BasePresenter implements TopListContract.Presenter {

    private static final String TAG = "TopListPresenter";

    @Inject
    TopListModel model;

    private TopListContract.View view;

    public TopListPresenter(TopListContract.View view) {
        super();
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void loadFirstPage() {
        Observable<List<TopEntry>> observable;
        if (!model.isItemsReceived()) {
            observable = model.getFirstPage();
            Log.d(TAG, "loadFirstPage: from network");
        } else {
            observable = model.getCachedData();
            Log.d(TAG, "loadFirstPage: from cache");
        }

        Disposable disposable = observable.subscribe(
                topEntries -> {
                    view.showReceivedItems(topEntries);
                    view.setRefreshing(false);
                    if (!model.isItemsReceived()) {
                        model.addItemsToCache(topEntries);
                    }
                },
                        throwable -> {
                            throwable.printStackTrace();
                            view.showError();
                            view.setRefreshing(false);
                        }
        );
        addSubscription(disposable);
    }

    @Override
    public void loadNextPage() {

    }

    @Override
    public void refreshList() {
        //TODO implement this
    }
}
