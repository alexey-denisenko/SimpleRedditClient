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

    public TopListPresenter(TopListContract.View view) {
        super();
        App.getComponent().inject(this);
        this.view = view;
    }

    @Override
    public void loadInitial() {
        Disposable disposable = model.getTopEntriesList(10, "")
                .map(topListMapper)
                .subscribe(topEntries -> view.addItems(topEntries),
                        throwable -> {
                            throwable.printStackTrace();
                            view.showError();
                        });


        addSubscription(disposable);
    }

    @Override
    public void refreshList() {
        //TODO implement this
    }
}
