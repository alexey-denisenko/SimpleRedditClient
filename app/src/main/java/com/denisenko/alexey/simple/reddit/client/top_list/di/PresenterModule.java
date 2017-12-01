package com.denisenko.alexey.simple.reddit.client.top_list.di;

import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    TopListModel provideModel() {
        return new TopListModel();
    }
}
