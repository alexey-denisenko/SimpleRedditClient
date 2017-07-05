package com.denisenko.alexey.simple.reddit.client;

import com.denisenko.alexey.simple.reddit.client.common.BasePresenter;
import com.denisenko.alexey.simple.reddit.client.common.di.CommonModule;
import com.denisenko.alexey.simple.reddit.client.top.di.ModelModule;
import com.denisenko.alexey.simple.reddit.client.top.di.PresenterModule;
import com.denisenko.alexey.simple.reddit.client.top.di.ViewModule;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListFragment;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListModel;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {CommonModule.class, ModelModule.class,
        PresenterModule.class, ViewModule.class})
public interface AppComponent {

    void inject(BasePresenter basePresenter);

    void inject(TopListPresenter topListPresenter);

    void inject(TopListModel topListModel);

    void inject(TopListFragment fragment);
}
