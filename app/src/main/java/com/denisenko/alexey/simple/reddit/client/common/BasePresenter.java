package com.denisenko.alexey.simple.reddit.client.common;

import com.denisenko.alexey.simple.reddit.client.App;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter implements BaseContract.Presenter {

    @Inject
    CompositeDisposable compositeDisposable;

    public BasePresenter() {
        App.getComponent().inject(this);
    }

    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}
