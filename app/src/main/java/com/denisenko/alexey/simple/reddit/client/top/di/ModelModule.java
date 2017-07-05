package com.denisenko.alexey.simple.reddit.client.top.di;

import com.denisenko.alexey.simple.reddit.client.Const;
import com.denisenko.alexey.simple.reddit.client.top.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top.api.ApiModule;
import com.denisenko.alexey.simple.reddit.client.top.mappers.RedditToChildList;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Module
public class ModelModule {

    @Provides
    @Singleton
    ApiInterface provideApiInterface() {
        return ApiModule.getApiInterface(Const.BASE_URl);
    }

    @Provides
    RedditToChildList providesRedditToChildListMapper() {
        return new RedditToChildList();
    }

    @Provides
    @Singleton
    @Named(Const.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(Const.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }

}
