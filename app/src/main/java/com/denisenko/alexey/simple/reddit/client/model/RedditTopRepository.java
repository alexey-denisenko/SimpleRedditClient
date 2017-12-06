package com.denisenko.alexey.simple.reddit.client.model;

import com.denisenko.alexey.simple.reddit.client.dagger.NamingConstants;
import com.denisenko.alexey.simple.reddit.client.entity.gson.Child;
import com.denisenko.alexey.simple.reddit.client.model.data.RedditApi;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class RedditTopRepository {

    private static final int MAXIMUM_ITEMS_COUNT = 50; //TODO Limit max size
    private static final int ITEMS_PER_PAGE = 10;

    private RedditApi redditApi;
    private Scheduler uiThread;
    private Scheduler ioThread;

    private List<Child> redditChildrens = new ArrayList<>();

    public RedditTopRepository(RedditApi redditApi,
                               @Named(NamingConstants.UI_THREAD) Scheduler uiThread,
                               @Named(NamingConstants.IO_THREAD) Scheduler ioThread) {

        this.redditApi = redditApi;
        this.uiThread = uiThread;
        this.ioThread = ioThread;
    }

    public Observable<List<Child>> getNextPage(boolean first, boolean forceRefresh) {

        if(forceRefresh) {
            redditChildrens.clear();
        }

        if(first && !redditChildrens.isEmpty()) {
            return getCachedData();
        } else {
            return getPage();
        }
    }

    private String getLastChildName() {

        if (redditChildrens.size() > 0) {
            return redditChildrens.get(redditChildrens.size() - 1).getData().getName();
        }
        return "";
    }

    private Observable<List<Child>> getPage() {
        return redditApi.getRedditTop(ITEMS_PER_PAGE, getLastChildName())
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .map(reddit -> reddit.getData().getChildrens())
                .doOnNext(this::addItemsToCache);
    }

    private Observable<List<Child>> getCachedData() {
        return Observable.just(redditChildrens);
    }

    private void addItemsToCache(List<Child> childrens) {
        redditChildrens.addAll(childrens);
    }
}
