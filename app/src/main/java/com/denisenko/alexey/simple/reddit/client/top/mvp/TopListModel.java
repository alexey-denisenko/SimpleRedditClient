package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.App;
import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.top.api.ApiInterface;
import com.denisenko.alexey.simple.reddit.client.top.mappers.RedditToChildList;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TopListModel implements TopListContract.Model {

    @Inject
    ApiInterface apiInterface;

    @Inject
    RedditToChildList redditToChildListMapper;

    public TopListModel() {
        App.getComponent().inject(this);
    }

    @Override
    public Observable<List<Child>> getTopEntriesList(int limit, String after) {
        return apiInterface.getTopEntries(limit, after)
                .map(redditToChildListMapper);
    }
}
