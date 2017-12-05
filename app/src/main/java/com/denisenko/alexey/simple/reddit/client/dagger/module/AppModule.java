package com.denisenko.alexey.simple.reddit.client.dagger.module;

import com.denisenko.alexey.simple.reddit.client.BuildConfig;
import com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants;
import com.denisenko.alexey.simple.reddit.client.model.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.model.data.RedditApi;
import com.denisenko.alexey.simple.reddit.client.model.data.ApiProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

@Module
public class AppModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    @Named(NamingConstants.UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(NamingConstants.IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }


    @Provides
    @Singleton
    RedditApi provideApiInterface() {
        return ApiProvider.getApiInterface(BuildConfig.REDDIT_ENDPOINT);
    }

    @Provides
    @Singleton
    InMemoryRepository provideInMemoryRepository() {
        return new InMemoryRepository();
    }
}
