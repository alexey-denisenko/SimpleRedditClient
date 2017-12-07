package com.denisenko.alexey.simple.reddit.client.presentation.top;

import com.denisenko.alexey.simple.reddit.client.common.BasePresenter;
import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;
import com.denisenko.alexey.simple.reddit.client.presentation.TopListContract;
import com.denisenko.alexey.simple.reddit.client.ui.TopListActivityCallback;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;


public class TopListPresenter extends BasePresenter implements TopListContract.Presenter {

    private TopListInteractor interactor;

    private final TopListContract.View view;

    private final TopListActivityCallback callback;

    @Inject
    public TopListPresenter(TopListContract.View view,
                            TopListInteractor interactor,
                            TopListActivityCallback callback,
                            CompositeDisposable compositeDisposable) {
        super(compositeDisposable);
        this.interactor = interactor;
        this.callback = callback;
        this.view = view;
    }


    @Override
    public void firstPage() {
        view.setRefreshing(true);

        Disposable disposable = interactor.getPage(true, false).subscribe(
                topEntries -> {
                    view.setRefreshing(false);
                    view.showFirstPage(topEntries);
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
    public void nextPage() {
        view.setRefreshing(true);

        Disposable disposable = interactor.getPage(false, false).subscribe(
                topEntries -> {
                    view.setRefreshing(false);
                    view.showNextPage(topEntries);
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

        addSubscription(disposable);    }

    @Override
    public void refreshList() {
        view.setRefreshing(true);

        Disposable disposable = interactor.getPage(true, true).subscribe(
                topEntries -> {
                    view.setRefreshing(false);
                    view.showFirstPage(topEntries);
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
    public boolean isLastPage() {
        return interactor.isLastPage();
    }

    @Override
    public void onItemClick(TopEntry entry) {
        callback.startWebViewActivity(entry);
    }
}
