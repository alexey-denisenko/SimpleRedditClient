package com.denisenko.alexey.simple.reddit.client.top_list.di;

import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListFragment;

import dagger.Component;

@Component(modules = {ViewModule.class})
public interface ViewComponent {

    void inject(TopListFragment topListFragment);
}
