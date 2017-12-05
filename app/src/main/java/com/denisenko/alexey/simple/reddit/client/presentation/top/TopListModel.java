package com.denisenko.alexey.simple.reddit.client.presentation.top;

import com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants;
import com.denisenko.alexey.simple.reddit.client.model.InMemoryRepository;
import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;
import com.denisenko.alexey.simple.reddit.client.model.data.RedditApi;
import com.denisenko.alexey.simple.reddit.client.presentation.TopListContract;

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
    private RedditApi redditApi;
    private Scheduler uiThread;
    private Scheduler ioThread;

    @Inject
    public TopListModel(InMemoryRepository inMemoryRepository,
                        TopListMapper topListMapper,
                        RedditApi redditApi,
                        @Named(NamingConstants.UI_THREAD) Scheduler uiThread,
                        @Named(NamingConstants.IO_THREAD) Scheduler ioThread) {

        this.inMemoryRepository = inMemoryRepository;
        this.topListMapper = topListMapper;
        this.redditApi = redditApi;
        this.uiThread = uiThread;
        this.ioThread = ioThread;
    }

    @Override
    public Observable<List<TopEntry>> getPage() {
        return redditApi.getTopEntries(ITEMS_PER_PAGE, inMemoryRepository.getLastEntryName())
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
