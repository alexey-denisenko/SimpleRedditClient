package com.denisenko.alexey.simple.reddit.client.dagger.module;

import com.denisenko.alexey.simple.reddit.client.model.InMemoryRepository;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants.IO_THREAD;
import static com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants.UI_THREAD;

@Module
public class AppModule {

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @Singleton
    @Named(UI_THREAD)
    Scheduler provideSchedulerUI() {
        return AndroidSchedulers.mainThread();
    }

    @Provides
    @Singleton
    @Named(IO_THREAD)
    Scheduler provideSchedulerIO() {
        return Schedulers.io();
    }


    @Provides
    @Singleton
    InMemoryRepository provideInMemoryRepository() {
        return new InMemoryRepository();
    }
}
