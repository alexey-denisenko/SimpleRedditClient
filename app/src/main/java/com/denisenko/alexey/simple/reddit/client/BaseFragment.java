package com.denisenko.alexey.simple.reddit.client;

import android.support.v4.app.Fragment;

import com.denisenko.alexey.simple.reddit.client.common.BaseContract;

public abstract class BaseFragment extends Fragment {

    protected abstract BaseContract.Presenter getPresenter();

    @Override
    public void onStop() {
        super.onStop();
        if (getPresenter() != null) {
            getPresenter().onStop();
        }
    }

}

