package com.denisenko.alexey.simple.reddit.client.top.di;

import com.denisenko.alexey.simple.reddit.client.top.mvp.TopListFragment;

import dagger.Component;

@Component(modules = {ViewModule.class})
public interface ViewComponent {

    void inject(TopListFragment topListFragment);
}
