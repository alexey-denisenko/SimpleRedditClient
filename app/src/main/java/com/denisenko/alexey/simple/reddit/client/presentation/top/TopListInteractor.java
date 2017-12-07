package com.denisenko.alexey.simple.reddit.client.presentation.top;

import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;
import com.denisenko.alexey.simple.reddit.client.model.RedditTopRepository;
import com.denisenko.alexey.simple.reddit.client.presentation.TopListContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

public class TopListInteractor implements TopListContract.Model {


    private RedditTopRepository redditTopRepository;
    private TopListMapper topListMapper;


    @Inject
    public TopListInteractor(RedditTopRepository redditTopRepository,
                             TopListMapper topListMapper) {

        this.redditTopRepository = redditTopRepository;
        this.topListMapper = topListMapper;
    }

    @Override
    public Observable<List<TopEntry>> getPage(boolean first, boolean forceRefresh) {

        return redditTopRepository.getNextPage(first, forceRefresh)
                .map(topListMapper);
    }

    @Override
    public boolean isLastPage() {
        return redditTopRepository.maxDataLimitReached();
    }
}
