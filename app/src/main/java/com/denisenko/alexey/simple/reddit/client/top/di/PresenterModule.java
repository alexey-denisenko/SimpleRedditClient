package com.denisenko.alexey.simple.reddit.client.top.di;

import com.denisenko.alexey.simple.reddit.client.top.mappers.TopListMapper;
import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    @Provides
    @Singleton
    TopListModel providesModel() {
        return new TopListModel();
    }

    @Provides
    TopListMapper provideMapper() {
        return new TopListMapper();
    }
}
