package com.denisenko.alexey.simple.reddit.client;

import android.app.Application;

public class App extends Application {

    private static AppComponent component;

    public static AppComponent getComponent() {
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        component = createComponent();
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .build();
    }
}
