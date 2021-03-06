package com.denisenko.alexey.simple.reddit.client.presentation;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.entity.TopEntry;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View extends BaseContract.View {

        void setRefreshing(boolean isLoading);

        void showFirstPage(List<TopEntry> items);

        void showNextPage(List<TopEntry> items);

        void showLoadFirstPageNetworkError();

        void showLoadFirstPageUnknownError();

        void showLoadNextPageNetworkError();

        void showLoadNextPageUnknownError();
    }

    interface Presenter extends BaseContract.Presenter {

        void firstPage();

        void nextPage();

        void refreshList();

        boolean isLastPage();

        void onItemClick(TopEntry entry);
    }

    interface Model extends BaseContract.Model {

        Observable<List<TopEntry>> getPage(boolean first, boolean forceRefresh);

        boolean isLastPage();
    }
}
