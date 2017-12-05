package com.denisenko.alexey.simple.reddit.client.top_list.di;

import com.denisenko.alexey.simple.reddit.client.top_list.mvp.TopListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {TopListModule.class})
@TopListScope
public interface TopListComponent {

    void inject(TopListFragment topListFragment);
}
