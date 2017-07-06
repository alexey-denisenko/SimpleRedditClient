package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View {

        void setRefreshing(boolean isLoading);

        void showReceivedItems(List<TopEntry> items);

        void showError();

        void stopPagination();


    }

    interface Presenter extends BaseContract.Presenter {

        void loadFirstPage();

        void loadNextPage();

        void refreshList();
    }

    interface Model {

        Observable<List<TopEntry>> getFirstPage();

        boolean isItemsReceived();

        Observable<List<TopEntry>> getCachedData();

        void addItemsToCache(List<TopEntry> topEntries);
    }
}
