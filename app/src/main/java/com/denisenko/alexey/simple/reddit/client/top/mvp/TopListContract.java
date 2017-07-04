package com.denisenko.alexey.simple.reddit.client.top.mvp;

import android.os.Bundle;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.pojo.RedditChildren;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View {

        void setRefreshing();

        void addItems();

        void showError();

        void stopPagination();
    }

    interface Presenter extends BaseContract.Presenter {

        void onLoadNextPage();

        void onRefresh();

        void onCreate(Bundle savedInstanceState);

        void onSaveInstanceState(Bundle outState);
    }

    interface Model {

        Observable<List<RedditChildren>> getTopEntriesList(int limit, String after);
    }
}
