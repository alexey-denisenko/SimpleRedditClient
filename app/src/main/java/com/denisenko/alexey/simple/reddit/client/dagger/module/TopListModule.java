package com.denisenko.alexey.simple.reddit.client.dagger.module;

import com.denisenko.alexey.simple.reddit.client.dagger.scope.TopListScope;
import com.denisenko.alexey.simple.reddit.client.model.RedditTopRepository;
import com.denisenko.alexey.simple.reddit.client.presentation.TopListContract;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListInteractor;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListMapper;
import com.denisenko.alexey.simple.reddit.client.presentation.top.TopListPresenter;
import com.denisenko.alexey.simple.reddit.client.ui.TopListActivityCallback;

import dagger.Module;
import dagger.Provides;
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
    TopListInteractor provideModel(RedditTopRepository redditTopRepository,
                                   TopListMapper topListMapper) {
        return new TopListInteractor(redditTopRepository, topListMapper);
    }

    @Provides
    TopListContract.Presenter provideTopListPresenter(TopListInteractor model, CompositeDisposable compositeDisposable) {
        return new TopListPresenter(view, model, callback, compositeDisposable);
    }
}
