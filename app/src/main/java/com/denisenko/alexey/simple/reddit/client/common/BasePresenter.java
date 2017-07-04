package com.denisenko.alexey.simple.reddit.client.common;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter implements BaseContract.Presenter {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected void addSubscription(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    public void onStop() {
        compositeDisposable.clear();
    }
}
