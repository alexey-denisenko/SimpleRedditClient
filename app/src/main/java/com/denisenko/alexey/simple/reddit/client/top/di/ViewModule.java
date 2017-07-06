package com.denisenko.alexey.simple.reddit.client.top.di;

import com.denisenko.alexey.simple.reddit.client.top.TopListActivityCallback;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListContract;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private TopListContract.View view;

    private TopListActivityCallback callback;

    public ViewModule(TopListContract.View view, TopListActivityCallback callback) {
        this.view = view;
        this.callback = callback;
    }

    @Provides
    TopListContract.Presenter provideTopListPresenter() {
        return new TopListPresenter(view, callback);
    }
}
