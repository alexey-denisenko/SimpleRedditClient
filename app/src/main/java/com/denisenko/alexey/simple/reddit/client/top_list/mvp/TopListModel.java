package com.denisenko.alexey.simple.reddit.client.top_list.mvp;

import com.denisenko.alexey.simple.reddit.client.Const;
import com.denisenko.alexey.simple.reddit.client.top_list.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.top_list.TopEntry;
import com.denisenko.alexey.simple.reddit.client.top_list.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top_list.mappers.TopListMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class TopListModel implements TopListContract.Model {

    private static final int ITEMS_PER_PAGE = 10;

    private static final int MAXIMUM_ITEMS_COUNT = 50;

    private InMemoryRepository inMemoryRepository;
    private TopListMapper topListMapper;
    private ApiInterface apiInterface;
    private Scheduler uiThread;
    private Scheduler ioThread;

    @Inject
    public TopListModel(InMemoryRepository inMemoryRepository,
                        TopListMapper topListMapper,
                        ApiInterface apiInterface,
                        @Named(Const.UI_THREAD) Scheduler uiThread,
                        @Named(Const.IO_THREAD) Scheduler ioThread) {

        this.inMemoryRepository = inMemoryRepository;
        this.topListMapper = topListMapper;
        this.apiInterface = apiInterface;
        this.uiThread = uiThread;
        this.ioThread = ioThread;
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
