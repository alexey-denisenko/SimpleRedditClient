package com.denisenko.alexey.simple.reddit.client.dagger.component;

import com.denisenko.alexey.simple.reddit.client.dagger.module.TopListModule;
import com.denisenko.alexey.simple.reddit.client.dagger.scope.TopListScope;
import com.denisenko.alexey.simple.reddit.client.ui.top.TopListFragment;

import dagger.Subcomponent;

@Subcomponent(modules = {TopListModule.class})
@TopListScope
public interface TopListComponent {

    void inject(TopListFragment topListFragment);
}
