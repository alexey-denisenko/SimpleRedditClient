package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View {

        void setRefreshing(boolean isLoading);

        void showFirstPage(List<TopEntry> items);

        void showNextPage(List<TopEntry> items);

        void showLoadFirstPageNetworkError();

        void showLoadFirstPageUnknownError();

        void showLoadNextPageNetworkError();

        void showLoadNextPageUnknownError();

        void stopPagination();
    }

    interface Presenter extends BaseContract.Presenter {

        void loadFirstPage();

        void loadNextPage();

        void refreshList();

        void stopPagination();

        boolean isLastPage();
    }

    interface Model {

        void setPresenter(TopListContract.Presenter presenter);

        Observable<List<TopEntry>> getPage();

        boolean isItemsReceived();

        Observable<List<TopEntry>> getCachedData();

        void addItemsToCache(List<TopEntry> topEntries);

        boolean isPaginationStopped();

        void clearRepository();
    }
}
