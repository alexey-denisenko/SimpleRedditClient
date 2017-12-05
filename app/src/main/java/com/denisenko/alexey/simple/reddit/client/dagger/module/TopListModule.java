package com.denisenko.alexey.simple.reddit.client.dagger.module;

import com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants;
import com.denisenko.alexey.simple.reddit.client.dagger.scope.TopListScope;
import com.denisenko.alexey.simple.reddit.client.model.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.ui.TopListActivityCallback;
import com.denisenko.alexey.simple.reddit.client.model.data.RedditApi;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListMapper;
import com.denisenko.alexey.simple.reddit.client.presentation.TopListContract;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListModel;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListPresenter;

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
                              RedditApi redditApi,
                              @Named(NamingConstants.UI_THREAD) Scheduler uiThread,
                              @Named(NamingConstants.IO_THREAD) Scheduler ioThread) {
        return new TopListModel(inMemoryRepository, topListMapper, redditApi, uiThread, ioThread);
    }

    @Provides
    TopListContract.Presenter provideTopListPresenter(TopListModel model, CompositeDisposable compositeDisposable) {
        return new TopListPresenter(view, model, callback, compositeDisposable);
    }
}
