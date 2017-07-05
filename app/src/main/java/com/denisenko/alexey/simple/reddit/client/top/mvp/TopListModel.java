package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.Const;
import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.top.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top.mappers.RedditToChildList;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public class TopListModel implements TopListContract.Model {

    @Inject
    ApiInterface apiInterface;

    @Inject
    RedditToChildList redditToChildListMapper;

    @Inject
    @Named(Const.UI_THREAD)
    Scheduler uiThread;

    @Inject
    @Named(Const.IO_THREAD)
    Scheduler ioThread;

    public TopListModel() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Child>> getTopEntriesList(int limit, String after) {
        return apiInterface.getTopEntries(limit, after)
                .subscribeOn(ioThread)
                .observeOn(uiThread)
                .map(redditToChildListMapper);
    }
}
