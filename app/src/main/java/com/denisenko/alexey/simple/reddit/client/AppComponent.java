package com.denisenko.alexey.simple.reddit.client;

import com.denisenko.alexey.simple.reddit.client.top_list.di.TopListComponent;
import com.denisenko.alexey.simple.reddit.client.top_list.di.TopListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    TopListComponent plus(TopListModule topListModule);
}
