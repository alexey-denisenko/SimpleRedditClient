package com.denisenko.alexey.simple.reddit.client.top.mvp;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;
import com.denisenko.alexey.simple.reddit.client.pojo.RedditChildren;

import java.util.List;

import io.reactivex.Observable;

public interface TopListContract {

    interface View {

    }

    interface Presenter extends BaseContract.Presenter {
        void onLoadNextPage();

    }

    interface Model {
        Observable<List<RedditChildren>> getTopEntriesList(int limit, String after);

    }
}
