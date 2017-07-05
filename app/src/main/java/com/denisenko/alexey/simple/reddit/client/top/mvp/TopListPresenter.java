package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.common.BasePresenter;
import com.denisenko.alexey.simple.reddit.client.top.mappers.TopListMapper;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;


public class TopListPresenter extends BasePresenter implements TopListContract.Presenter {

    @Inject
    TopListModel model;

    @Inject
    TopListMapper topListMapper;

    private TopListContract.View view;

    public TopListPresenter() {
        super();
        App.getComponent().inject(this);
    }

    public TopListPresenter(TopListContract.View view) {
        this.view = view;
    }

    @Override
    public void loanInitial() {
        Disposable disposable = model.getTopEntriesList(10, "")
                .map(topListMapper)
                .subscribe(topEntries -> view.addItems(topEntries),
                        throwable -> view.showError());


        addSubscription(disposable);
    }

    @Override
    public void refreshList() {
        //TODO implement this
    }
}
