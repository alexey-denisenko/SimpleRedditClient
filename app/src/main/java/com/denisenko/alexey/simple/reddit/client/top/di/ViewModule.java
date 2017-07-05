package com.denisenko.alexey.simple.reddit.client.top.di;

import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListContract;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class ViewModule {

    private TopListContract.View view;

    public ViewModule(TopListContract.View view) {
        this.view = view;
    }

    @Provides
    TopListContract.Presenter provideTopListPresenter() {
        return new TopListPresenter(view);
    }
}
