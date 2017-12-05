package com.denisenko.alexey.simple.reddit.client.common;

import android.support.v4.app.Fragment;

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

