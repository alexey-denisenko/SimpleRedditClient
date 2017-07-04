package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.pojo.Child;
import com.denisenko.alexey.simple.reddit.client.top.TopEntry;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View {

        void setRefreshing(boolean isLoading);

        void addItems(List<TopEntry> items);

        void showError();

        void stopPagination();
    }

    interface Presenter extends BaseContract.Presenter {

        void loadMore();

        void refreshList();
    }

    interface Model {

        Observable<List<Child>> getTopEntriesList(int limit, String after);
    }
}
