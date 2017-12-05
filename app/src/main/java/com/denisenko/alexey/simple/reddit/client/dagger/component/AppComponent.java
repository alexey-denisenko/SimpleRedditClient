package com.denisenko.alexey.simple.reddit.client.dagger.component;

import com.denisenko.alexey.simple.reddit.client.dagger.module.AppModule;
import com.denisenko.alexey.simple.reddit.client.dagger.module.ServerModule;
import com.denisenko.alexey.simple.reddit.client.dagger.module.TopListModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ServerModule.class})
public interface AppComponent {

    TopListComponent plus(TopListModule topListModule);
}
