package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.Const;
import com.denisenko.alexey.simple.reddit.client.top.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top.mappers.TopListMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class TopListModel implements TopListContract.Model {

    private static final int ITEMS_PER_PAGE = 10;

    private static final int MAXIMUM_ITEMS_COUNT = 50;

    @Inject
    ApiInterface apiInterface;

    @Inject
    TopListMapper topListMapper;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    @Inject
    InMemoryRepository inMemoryRepository;

    public TopListModel() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<TopEntry>> getPage() {
        return apiInterface.getTopEntries(ITEMS_PER_PAGE, inMemoryRepository.getLastEntryName())
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .map(topListMapper);
    }

    @Override
    public boolean isItemsReceived() {
        return inMemoryRepository != null && inMemoryRepository.getEntries().size() > 0;
    }

    @Override
    public Observable<List<TopEntry>> getCachedData() {
        return Observable.just(inMemoryRepository.getEntries());
    }

    @Override
    public void addItemsToCache(List<TopEntry> topEntries) {
        inMemoryRepository.addEntries(topEntries);
    }

    @Override
    public boolean isLastPage() {
        return inMemoryRepository.getEntries().size() == MAXIMUM_ITEMS_COUNT;
    }

    @Override
    public void clearRepository() {
        inMemoryRepository.clear();
    }
}
