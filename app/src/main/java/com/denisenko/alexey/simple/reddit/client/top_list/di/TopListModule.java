package com.denisenko.alexey.simple.reddit.client.top_list.di;

import com.denisenko.alexey.simple.reddit.client.Const;
import com.denisenko.alexey.simple.reddit.client.top_list.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.top_list.TopListActivityCallback;
import com.denisenko.alexey.simple.reddit.client.top_list.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top_list.mappers.TopListMapper;
import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListContract;
import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListModel;
import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class TopListModule {

    private final TopListContract.View view;

    private final TopListActivityCallback callback;

    public TopListModule(TopListContract.View view, TopListActivityCallback callback) {
        this.view = view;
        this.callback = callback;
    }

    @Provides
    @TopListScope
    TopListMapper provideMapper() {
        return new TopListMapper();
    }

    @Provides
    @TopListScope
    TopListModel provideModel(InMemoryRepository inMemoryRepository,
                              TopListMapper topListMapper,
                              ApiInterface apiInterface,
                              @Named(Const.UI_THREAD) Scheduler uiThread,
                              @Named(Const.IO_THREAD) Scheduler ioThread) {
        return new TopListModel(inMemoryRepository, topListMapper, apiInterface, uiThread, ioThread);
    }

    @Provides
    TopListContract.Presenter provideTopListPresenter(TopListModel model, CompositeDisposable compositeDisposable) {
        return new TopListPresenter(view, model, callback, compositeDisposable);
    }
}
